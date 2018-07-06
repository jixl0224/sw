package snsoft.sw.card.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import snsoft.bas.service.QueryResults;
import snsoft.bas.service.SaveParams;
import snsoft.bas.service.SaveResults;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.commons.util.DateUtils;
import snsoft.commons.util.InfoException;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.DefaultDAO;
import snsoft.dx.mc.service.MakeCodeService;
import snsoft.dx.mc.service.MakeCodeService.MakeCodeParam;
import snsoft.dx.vo.convert.service.MapperService;
import snsoft.sql.SqlValue;
import snsoft.sw.card.entity.Load;
import snsoft.sw.card.entity.LoadDetail;
import snsoft.sw.card.entity.PMUcard;
import snsoft.sw.card.service.CreditUIService;
import snsoft.sw.card.service.LoadService;

/**
 * <p>标题：筹款服务实现</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午10:06:55</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class LoadServiceImpl implements LoadService
{
	@Autowired
	private CreditUIService	creditService;

	@Autowired
	private MapperService	mapperService;

	@Autowired
	private MakeCodeService	makeCodeService;

	@Override
	public QueryResults<Load> query(LoadParams params)
	{
		List<Load> list = new DefaultDAO<Load>(Load.class).queryList(params.buildDBQueryParams());
		return new QueryResults<>(list);
	}

	@Override
	public SaveResults save(SaveParams<Load> params)
	{
		new DefaultDAO<Load>(Load.class).save(params.getSaveValues());
		return new SaveResults();
	}

	@Override
	public List<LoadDetail> queryDetail(LoadDetailParams params)
	{
		return new DefaultDAO<LoadDetail>(LoadDetail.class).queryList(params.buildDBQueryParams());
	}

	private void assertExists(int lid)
	{
		try (Database db = DBUtils.newDatabaseByTable("pm_cload", true))
		{

		}
	}

	@Override
	public void loadCards(int lid)
	{
		try (Database db = DBUtils.newDatabaseByTable("pm_cload", true))
		{
			// 确定记录存在
			String sql = db.getDialect().toExists("select 1 from pm_cload where lid=?");
			if (db.query1(sql, new SqlValue(lid)) == null)
			{
				throw new InfoException("记录不存在！");
			}
			// 删除原记录
			db.executeUpdate("delete from pm_cloadg where lid=?", new SqlValue(lid));
		}
		// 加载信用卡
		PMUcard[] cards = creditService.queryPMUcard(new NullQueryParams());
		// 子表分码
		int[] codes;
		{
			MakeCodeParam param = new MakeCodeParam("pm_cloadg", "gid");
			param.setCount(cards.length);
			codes = makeCodeService.makeCodesAsInt(param);
		}
		// 生成子表数据
		List<LoadDetail> details = new ArrayList<>();
		for (PMUcard card : cards)
		{
			LoadDetail detail = new LoadDetail();
			mapperService.mapper(card, detail);
			detail.setLid(lid);
			detail.setGid(codes[details.size()]);
			details.add(detail);
		}
		new DefaultDAO<LoadDetail>(LoadDetail.class).insert(details.toArray(new LoadDetail[0]));
	}

	@Override
	public void calcCards(int lid)
	{
		//
		Load load = new DefaultDAO<Load>(Load.class).queryByID(false, lid);
		if (load == null)
		{
			throw new InfoException("记录不存在！");
		}
		List<LoadDetail> details = load.getDetails();
		// 无息天数
		Date odate = load.getOdate();
		int day = DateUtils.getDateDay(odate);
		for (LoadDetail detail : details)
		{
			int sdate = detail.getSdate();
			int x;
			if (sdate >= day)
			{
				// 20 + (sdate-day)
				x = 20 + (sdate - day);
			} else
			{
				// 50 - (day-sdate)
				x = 50 - (day - sdate);
			}
			detail.setCdays(x);
			detail.addStoredColumn("cdays");
		}
		// 存盘
		new DefaultDAO<LoadDetail>(LoadDetail.class).update(details.toArray(new LoadDetail[0]));
	}

}
