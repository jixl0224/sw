package snsoft.sw.card.service;

import java.util.Date;
import java.util.List;
import snsoft.bas.service.QueryParams;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.commons.annotation.AuthParam;
import snsoft.commons.spring.SpringBean;
import snsoft.sw.card.entity.Activity;
import snsoft.sw.card.entity.CardView;
import snsoft.sw.card.entity.PMCcard;
import snsoft.sw.card.entity.PMUcard;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月15日上午10:45:29</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "SN-PM.CreditUIService")
public interface CreditUIService
{
	PMCcard[] queryPMCcard(NullQueryParams param);

	void savePMCcard(PMCcard[] ccards);

	@AuthParam(sheetCode = "CD.Ucode", opids = { "R", "C" })
	PMUcard[] queryPMUcard(NullQueryParams param);

	@AuthParam(sheetCode = "CD.Ucode", opids = { "C" })
	void savePMUcard(PMUcard[] ucards);

	CardView[] queryCardView(NullQueryParams param);

	@AuthParam(sheetCode = "CD.Ucode", opids = { "R", "C" })
	List<Activity> queryActivity(ActivityParams params);

	@AuthParam(sheetCode = "CD.Ucode", opids = { "C" })
	void saveActivity(List<Activity> activities);

	public static class ActivityParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4155508805877391427L;
		private Date				odate;

		public Date getOdate()
		{
			return odate;
		}

		public void setOdate(Date odate)
		{
			this.odate = odate;
		}
	}
}
