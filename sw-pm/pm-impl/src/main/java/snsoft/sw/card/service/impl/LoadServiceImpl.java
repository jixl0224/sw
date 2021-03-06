package snsoft.sw.card.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import snsoft.api.bas.NullQueryParams;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.commons.util.DateUtils;
import snsoft.commons.util.InfoException;
import snsoft.dx.DAO;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.mc.service.MakeCodeService;
import snsoft.dx.mc.service.MakeCodeService.MakeCodeParam;
import snsoft.dx.util.ParamUtils;
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
		List<Load> list = DAO.newInstance(Load.class).queryList(ParamUtils.buildDBQueryParams(params));
		return new QueryResults<>(list);
	}

	@Override
	public SaveResults save(SaveParams<Load> params)
	{
		DAO.newInstance(Load.class).save(params.getSaveValues());
		return new SaveResults();
	}

	@Override
	public List<LoadDetail> queryDetail(LoadDetailParams params)
	{
		return DAO.newInstance(LoadDetail.class).queryList(ParamUtils.buildDBQueryParams(params));
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
		DAO.newInstance(LoadDetail.class).insert(details.toArray(new LoadDetail[0]));
	}

	@Override
	public void calcCards(int lid)
	{
		//
		Load load = DAO.newInstance(Load.class).queryByID(lid, false);
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
		DAO.newInstance(LoadDetail.class).update(details.toArray(new LoadDetail[0]));
	}

}
