package snsoft.sw.card.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.bas.service.util.Sort;
import snsoft.commons.login.UserSession;
import snsoft.commons.util.DateUtils;
import snsoft.commons.util.StrUtils;
import snsoft.context.AppContext;
import snsoft.dx.Database;
import snsoft.dx.DefaultDAO;
import snsoft.dx.QueryColumn;
import snsoft.sql.SqlExpr;
import snsoft.sw.card.entity.CardView;
import snsoft.sw.card.entity.PMCcard;
import snsoft.sw.card.entity.PMUcard;
import snsoft.sw.card.service.CreditUIService;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月15日上午10:58:56</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CreditUIServiceImpl implements CreditUIService
{
	@Override
	public PMCcard[] queryPMCcard(NullQueryParams param)
	{
		return new DefaultDAO<PMCcard>().query(PMCcard.class, param.buildDBQueryParams());
	}

	@Override
	public void savePMCcard(PMCcard[] ccards)
	{
		new DefaultDAO<PMCcard>().save(ccards);
	}

	@Override
	public PMUcard[] queryPMUcard(NullQueryParams param)
	{
		param.setQueryColumns(null);
		param.setSort(new Sort("ccode"));
		param.addExtQueryParams(new QueryColumn("ucode", AppContext.getUserSession(true).getUserCode()));
		return new DefaultDAO<PMUcard>().query(PMUcard.class, param.buildDBQueryParams());
	}

	@Override
	public void savePMUcard(PMUcard[] ucards)
	{
		new DefaultDAO<PMUcard>().save(ucards);
	}

	@Override
	public CardView[] queryCardView(NullQueryParams param)
	{
		UserSession userSession = AppContext.getUserSession(true);
		SqlExpr filter = SqlExpr.columnEqValue("ucode", userSession.getUserCode());
		String sql = "select ccode,sdate,ddate";
		sql += " from pm_ucard";
		sql += " where " + filter;
		Object[][] values;
		try (Database db = userSession.newDatabaseByTable("pm_ucard", true))
		{
			values = db.query3(sql);
		}
		Date odate = DateUtils.getServerDate();
		int year = DateUtils.getDateYear(odate);
		int month = DateUtils.getDateMonth(odate);
		List<CardView> list = new ArrayList<>();
		for (Object[] vs : values)
		{
			CardView v1 = new CardView();
			v1.setCcode((String) vs[0]);
			int sdate = StrUtils.obj2int(vs[1]);
			v1.setOdate(sdate);
			v1.setDatetype("S");
			list.add(v1);
			//
			CardView v2 = new CardView();
			v2.setCcode((String) vs[0]);
			int ddate = StrUtils.obj2int(vs[2]);
			v2.setDatetype("D");
			Date date = DateUtils.toDate(year, month - 1, sdate);
			date = DateUtils.incDate(date, ddate);
			v2.setOdate(DateUtils.getDateDay(date));
			list.add(v2);
		}
		Collections.sort(list, (o1, o2) -> {

			int c = o1.getCcode().compareTo(o2.getCcode());
			if (c != 0)
			{
				return c;
			}
			return o1.getOdate() - o2.getOdate();
		});
		return list.toArray(new CardView[list.size()]);
	}
}
