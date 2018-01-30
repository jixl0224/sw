package snsoft.sw.card.service.impl;

import java.util.Map;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.bas.service.util.Sort;
import snsoft.commons.login.UserSession;
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
		String sql = "select ccode,sdate odate,'S' datetype";
		sql += " from pm_ucard";
		sql += " where " + filter;
		sql += " union ";
		sql += " select ccode,ddate odate,'D' datetype";
		sql += " from pm_ucard";
		sql += " where " + filter;
		sql += " order by 1,2";
		try (Database db = userSession.newDatabaseByTable("pm_ucard", true))
		{
			return db.query(CardView.class, sql, (Map<String,?>) null);
		}
	}
}
