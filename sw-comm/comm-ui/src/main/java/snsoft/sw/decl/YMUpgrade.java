package snsoft.sw.decl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import snsoft.commons.util.DateUtils;
import snsoft.commons.util.Progress;
import snsoft.commons.util.StrUtils;
import snsoft.dx.DBUtils;
import snsoft.dx.UpdateData;
import snsoft.sql.SqlExpr;
import snsoft.sql.SqlValue;

/**
 * <p>标题：月表处理程序</p>
 * <p>功能：</p>
 * <p>
 * tblname=""
 * grpCols=""
 * bean = new snsoft.sw.decl.YMUpgrade(getProgress(),tblname,grpCols)
 * bean.upgrade()
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年1月19日下午12:28:46</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class YMUpgrade
{
	protected final Progress	progress;
	protected final String		tblname;
	protected final String[]	grpCols;

	public YMUpgrade(Progress progress, String tblname, String grpCols)
	{
		super();
		this.progress = progress;
		this.tblname = tblname;
		this.grpCols = grpCols.split(",");
	}

	protected void upgradeData(Date date, Object[][] values)
	{

		DBUtils.trans(tblname, db -> {
			UpdateData updateData = new UpdateData();
			String ym = new SimpleDateFormat("YYMM").format(date);
			int i = 0;
			for (Object[] vs : values)
			{
				Map<String,Object> data = new HashMap<>();
				for (int j = 0; j < grpCols.length; j++)
				{
					data.put(grpCols[j], vs[j]);
				}
				data.put("cnt", vs[grpCols.length]);
				data.put("ym", ym);
				updateData.addInsert(tblname, data);
				if (++i % 1000 == 0)
				{
					db.updateData(updateData, true);
					updateData.clear();
				}
			}
			if (updateData.size() > 0)
			{
				db.updateData(updateData, true);
			}
		});

	}

	public void upgrade()
	{
		String ym = getMaxYM(tblname);
		progress.addProgressMessage("起始年月：" + ym);
		deleteMaxYM(tblname, ym);
		getYMs(ym).forEach(from -> {
			Object[][] values = DBUtils.read("sw_decl", db -> {
				Date to = DateUtils.incMonth(from, 1);
				String join = StrUtils.join(grpCols, ',');
				String sql = "select " + join + ",sum(cnt)";
				sql += " from sw_decl";
				SqlExpr filter = new SqlExpr(SqlExpr.GE, SqlExpr.id("decl_date"), SqlExpr.constExpr(from));
				filter = filter.and(new SqlExpr(SqlExpr.LT, SqlExpr.id("decl_date"), SqlExpr.constExpr(to)));
				sql += " where " + filter.toString(db.getDialect());
				sql += " group by " + join;
				return db.query3(sql);
			});
			upgradeData(from, values);
			progress.addProgressMessage(String.format("%1$tF=%2$d", from, values.length));
		});
	}

	protected String getMaxYM(String tblname)
	{
		return DBUtils.read(tblname, db -> {
			String sql = "select max(ym) from " + tblname;
			String ym = (String) db.query1(sql);
			if (ym == null)
			{
				sql = "select min(to_char(decl_date,'yymm')) from sw_decl";
				ym = (String) db.query1(sql);
			}
			return ym;
		});
	}

	protected void deleteMaxYM(String tblname, String ym)
	{
		DBUtils.trans(tblname, db -> {
			String sql = "delete from " + tblname + " where ym=?";
			db.executeUpdate(sql, new SqlValue(ym));
		});
	}

	protected List<Date> getYMs(String ym)
	{
		Date fromDate;
		{
			int y = Integer.parseInt(ym.substring(0, 2));
			int m = Integer.parseInt(ym.substring(2));
			fromDate = DateUtils.toDate(2000 + y, m, 1);
		}
		Date today = DateUtils.getServerDate(true);
		List<Date> list = new ArrayList<>();
		while (fromDate.compareTo(today) < 0)
		{
			list.add(fromDate);
			fromDate = DateUtils.incMonth(fromDate, 1);
		}
		return list;
	}

}
