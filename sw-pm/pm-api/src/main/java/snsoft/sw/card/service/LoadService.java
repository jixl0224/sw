package snsoft.sw.card.service;

import java.util.List;
import snsoft.api.bas.QueryParams;
import snsoft.api.bas.QueryResults;
import snsoft.api.bas.SaveParams;
import snsoft.api.bas.SaveResults;
import snsoft.api.service.Remoteable;
import snsoft.api.service.SpringBean;
import snsoft.api.sql.SqlColumn;
import snsoft.sw.card.entity.Load;
import snsoft.sw.card.entity.LoadDetail;

/**
 * <p>标题：筹款服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午10:01:05</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "SN-PM.LoadService")
public interface LoadService
{
	/**
	 * 筹款主表查询服务
	 * @param params
	 * @return
	 */
	QueryResults<Load> query(LoadParams params);

	/**
	 * 筹款存盘服务
	 * @param params
	 * @return
	 */
	SaveResults save(SaveParams<Load> params);

	/**
	 * 查询筹款明细
	 * @return
	 */
	List<LoadDetail> queryDetail(LoadDetailParams params);

	/**
	 * 加载信用卡
	 * @param lid
	 */
	@Remoteable
	void loadCards(int lid);

	/**
	 * 计算筹款金额
	 * @param lid
	 */
	@Remoteable
	void calcCards(int lid);

	public static class LoadParams extends QueryParams
	{
		private static final long serialVersionUID = -137273066241805338L;
	}

	public static class LoadDetailParams extends QueryParams
	{
		private static final long	serialVersionUID	= 1665564950644807590L;

		@SqlColumn(flags = 8)
		private int					lid;

		public int getLid()
		{
			return lid;
		}

		public void setLid(int lid)
		{
			this.lid = lid;
		}
	}
}
