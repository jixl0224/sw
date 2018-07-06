package snsoft.sw.card.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.bas.service.util.ParamUtils;
import snsoft.commons.util.DataUtils;
import snsoft.commons.util.DateUtils;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.DefaultDAO;
import snsoft.dx.QueryColumn;
import snsoft.dx.vo.convert.service.MapperService;
import snsoft.sql.SqlExpr;
import snsoft.sql.SqlValue;
import snsoft.sw.card.entity.Consume;
import snsoft.sw.card.entity.PMUcard;
import snsoft.sw.card.entity.Repayment;
import snsoft.sw.card.service.BalanceService;
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
 * <p>创建日期：2018年3月11日上午9:44:40</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class BalanceServiceImpl implements BalanceService
{
	@Resource(name = "SN-PM.CreditUIService")
	private CreditUIService	creditService;

	@Autowired
	private MapperService	mapperService;

	@Override
	public List<Consume> queryConsume(BalanceParams params)
	{
		buildParams(params);
		return new DefaultDAO<Consume>(Consume.class).queryList(ParamUtils.buildDBQueryParams(params));
	}

	private void buildParams(BalanceParams params)
	{
		Date odate = params.getOdate();
		int year = DateUtils.getDateYear(odate);
		int month = DateUtils.getDateMonth(odate);
		Date fmDate = DateUtils.toDate(year, month, 1);
		Date toDate = DateUtils.toDate(year, month + 1, 0);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		params.addExtQueryParams(new QueryColumn("odate", SqlExpr.GE, fmt.format(fmDate)));
		params.addExtQueryParams(new QueryColumn("odate", SqlExpr.LE, fmt.format(toDate)));
	}

	@Override
	public List<Repayment> queryRepayment(BalanceParams params)
	{
		buildParams(params);
		return new DefaultDAO<Repayment>(Repayment.class).queryList(ParamUtils.buildDBQueryParams(params));
	}

	@Override
	public List<Bill> queryBill(BalanceParams params)
	{
		PMUcard[] cards = creditService.queryPMUcard(new NullQueryParams());
		List<Bill> bills = new ArrayList<>(cards.length);
		try (Database db = DBUtils.newDatabaseByTable("pm_consume", true))
		{
			for (PMUcard card : cards)
			{
				Bill bill = new Bill();
				mapperService.mapper(card, bill);
				queryBill(db, card, bill, params.getOdate(), params.getDays());
				bills.add(bill);
			}
		}
		return bills;
	}

	private void queryBill(Database db, PMUcard card, Bill bill, Date odate, int refdays)
	{
		int year = DateUtils.getDateYear(odate);
		int month = DateUtils.getDateMonth(odate);
		int day = DateUtils.getDateDay(odate);
		// 还款日
		{
			Date date = DateUtils.toDate(year, month - 1, bill.getSdate());
			date = DateUtils.incDate(date, card.getDdate());
			bill.setDdate(DateUtils.getDateDay(date));
		}
		// 天数
		{
			// 账单日
			if (day <= card.getSdate())
			{
				bill.setSdays(card.getSdate() - day);
			} else
			{
				Date fm = DateUtils.toDate(year, month + 1, card.getSdate());
				bill.setSdays(DateUtils.diffDate(fm, odate));
			}
			// 还款日
			if (day <= bill.getDdate())
			{
				bill.setDdays(bill.getDdate() - day);
			} else
			{
				Date fm = DateUtils.toDate(year, month + 1, bill.getDdate());
				bill.setDdays(DateUtils.diffDate(fm, odate));
			}
		}
		Date[] dates = new Date[2];
		if (day <= card.getSdate())
		{
			dates[0] = DateUtils.toDate(year, month - 2, card.getSdate());
			dates[1] = DateUtils.toDate(year, month - 1, card.getSdate());
		} else
		{
			dates[0] = DateUtils.toDate(year, month - 1, card.getSdate());
			dates[1] = DateUtils.toDate(year, month, card.getSdate());
		}
		// 已出账
		{
			String sql = "select sum(fcy) from pm_consume where ccode=?";
			sql += " and " + buildDateFilter(dates[0], dates[1]).toString(db.getDialect());
			bill.setFcy1(DataUtils.obj2big(db.query1(sql, new SqlValue(card.getCcode()))));
		}
		// 未出账
		{
			String sql = "select sum(fcy) from pm_consume where ccode=?";
			sql += " and " + buildDateFilter(DateUtils.incDate(dates[1], 1), odate).toString(db.getDialect());
			bill.setFcy2(DataUtils.obj2big(db.query1(sql, new SqlValue(card.getCcode()))));
		}
		// 已还款
		{
			String sql = "select sum(fcy) from pm_repayment where ccode=?";
			sql += " and " + buildDateFilter(dates[1], odate).toString(db.getDialect());
			bill.setFcy3(DataUtils.obj2big(db.query1(sql, new SqlValue(card.getCcode()))));
		}
		// 未还款
		{
			bill.setFcy6(bill.getFcy1().subtract(bill.getFcy3()));
		}
		// 剩余额度
		{
			BigDecimal fcy4 = card.getCline().subtract(bill.getFcy1()).subtract(bill.getFcy2()).add(bill.getFcy3());
			bill.setFcy4(fcy4);
		}
		// 消费占比
		{
			BigDecimal ratio1 = card.getCline().subtract(bill.getFcy4()).divide(card.getCline(), 4, BigDecimal.ROUND_HALF_UP);
			bill.setRatio1(ratio1);
		}
		// 日均建议
		{
			Date date = DateUtils.toDate(year, day < card.getSdate() ? month - 1 : month, card.getSdate());
			int days = DateUtils.diffDate(odate, date);
			BigDecimal half = card.getCline().divide(new BigDecimal("2"));
			if (days < refdays && bill.getFcy2().subtract(half).signum() < 0)
			{
				BigDecimal fcy = half.subtract(bill.getFcy4()).signum() > 0 ? bill.getFcy4() : half;
				if ((fcy = fcy.subtract(new BigDecimal("2000"))).signum() > 0)
				{
					BigDecimal fcy5 = fcy.divide(new BigDecimal(refdays - days), 0, BigDecimal.ROUND_HALF_UP);
					bill.setFcy5(fcy5);
				}
			}
		}
	}

	private SqlExpr buildDateFilter(Date fmDate, Date toDate)
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		SqlExpr fm = new SqlExpr(SqlExpr.GE, SqlExpr.id("odate"), SqlExpr.constExpr(fmt.format(fmDate)));
		SqlExpr to = new SqlExpr(SqlExpr.LE, SqlExpr.id("odate"), SqlExpr.constExpr(fmt.format(toDate)));
		return fm.and(to);
	}
}
