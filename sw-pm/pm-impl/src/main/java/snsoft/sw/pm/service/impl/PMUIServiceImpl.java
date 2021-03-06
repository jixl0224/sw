package snsoft.sw.pm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import snsoft.api.bas.NullQueryParams;
import snsoft.api.bas.Sort;
import snsoft.dx.DAO;
import snsoft.dx.util.ParamUtils;
import snsoft.sql.SqlExpr;
import snsoft.sw.pm.entity.Actor;
import snsoft.sw.pm.entity.PActor;
import snsoft.sw.pm.entity.PMView;
import snsoft.sw.pm.entity.PMView.Info;
import snsoft.sw.pm.entity.Project;
import snsoft.sw.pm.service.PMUIService;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月9日下午2:36:22</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class PMUIServiceImpl implements PMUIService
{
	@Override
	public Actor[] queryActors(NullQueryParams param)
	{
		return DAO.newInstance(Actor.class).query(ParamUtils.buildDBQueryParams(param));
	}

	@Override
	public void saveActors(Actor[] actors)
	{
		DAO.newInstance(Actor.class).save(actors);
	}

	@Override
	public Project[] queryProjects(NullQueryParams param)
	{
		return DAO.newInstance(Project.class).query(ParamUtils.buildDBQueryParams(param));
	}

	@Override
	public PActor[] queryPActors(PActorParam param)
	{
		return DAO.newInstance(PActor.class).query(ParamUtils.buildDBQueryParams(param));
	}

	@Override
	public void saveProjects(Project[] projects)
	{
		DAO.newInstance(Project.class).save(projects);
	}

	@Override
	public PMView[] queryPointViews(PMPointParam param)
	{
		if (param.filterInvalid())
		{
			List<SqlExpr> filter = new ArrayList<>();
			filter.add(SqlExpr.columnOpValue(SqlExpr.LE, "bedate", param.getDate()));
			filter.add(SqlExpr.columnIsNull("ledate").or(SqlExpr.columnOpValue(SqlExpr.GE, "ledate", param.getDate())));
			param.setFilter(SqlExpr.and(filter));
		}
		param.setQueryColumns(null);
		param.setSort(new Sort("ucode", "pcode", "sortidx"));
		PActor[] pactors = DAO.newInstance(PActor.class).query(ParamUtils.buildDBQueryParams(param));
		Map<String,PMView> map = new TreeMap<>();
		for (PActor pa : pactors)
		{
			String key = pa.getUcode() + "-" + pa.getPcode();
			PMView pmv = map.get(key);
			if (pmv == null)
			{
				map.put(key, pmv = new PMView(pa.getUcode(), pa.getPcode()));
			}
			if (pa.getLedate() == null || pa.getLedate().compareTo(param.getDate()) > 0)
			{
				pa.setLedate(param.getDate());
			}
			pmv.getInfos().add(new Info(pa));
		}
		return map.values().toArray(new PMView[0]);
	}

	@Override
	public PMView[] queryPeriodViews(PMPeriodParam param)
	{
		if (param.filterInvalid())
		{
			//
			SqlExpr filter = SqlExpr.columnIsNull("ledate").or(SqlExpr.columnOpValue(SqlExpr.GE, "ledate", param.getDatefm()));
			filter = filter.and(SqlExpr.columnOpValue(SqlExpr.LT, "bedate", param.getDateto()));
			//
			param.setFilter(filter);
		}
		param.setQueryColumns(null);
		param.setSort(new Sort("ucode", "pcode", "sortidx"));
		PActor[] pactors = DAO.newInstance(PActor.class).query(ParamUtils.buildDBQueryParams(param));
		Map<String,PMView> map = new TreeMap<>();
		for (PActor pa : pactors)
		{
			String key = pa.getUcode() + "-" + pa.getPcode();
			PMView pmv = map.get(key);
			if (pmv == null)
			{
				map.put(key, pmv = new PMView(pa.getUcode(), pa.getPcode()));
			}
			if (pa.getLedate() == null || pa.getLedate().compareTo(param.getDateto()) > 0)
			{
				pa.setLedate(param.getDateto());
			}
			if (pa.getBedate().compareTo(param.getDatefm()) < 0)
			{
				pa.setBedate(param.getDatefm());
			}
			pmv.getInfos().add(new Info(pa));
		}
		return map.values().toArray(new PMView[0]);
	}
}
