package snsoft.sw.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import snsoft.commons.util.DateUtils;
import snsoft.dx.ReadDataSet;
import snsoft.dx.ReadDataSetFactory;
import snsoft.dx.dataset.ArrayReadDataSet;
import snsoft.dx.ui.CrossDataInfo;
import snsoft.ui.DefaultUIListener;
import snsoft.ui.UIComponent;
import snsoft.ui.UIEvent;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月15日下午3:57:06</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CreditViewListener extends DefaultUIListener
{
	@Override
	public Object getCodeNameMap(UIEvent event, UIComponent component)
	{
		if ("date".equals(component.uiname))
		{
			List<Object[]> list = new ArrayList<>();
			Date date = new Date();
			int dateYear = DateUtils.getDateYear(date);
			int dateMonth = DateUtils.getDateMonth(date);
			Date date1 = DateUtils.toDate(dateYear, dateMonth, 1);
			Date date2 = DateUtils.toDate(dateYear, dateMonth + 1, 1);
			int days = DateUtils.diffDate(date2, date1);
			for (int i = 0; i < days; i++)
			{
				list.add(new Object[] { i + 1, String.format("%1$02d", i + 1) });
			}
			return list.toArray(new Object[list.size()][]);
		}
		return super.getCodeNameMap(event, component);
	}

	@Override
	public ReadDataSet onDataLoaded(UIEvent event, ReadDataSet rs)
	{
		ArrayReadDataSet ars = ReadDataSetFactory.impl.toArrayReadDataSet(rs, true);
//		PMCcard[] cards;
//		{
//			CreditUIService service = SpringBeanUtils.getBeanByName("SN-HELP.CreditUIService");
//			NullQueryParams param = new NullQueryParams();
//			param.setSort(new Sort("code"));
//			cards = service.queryPMCcard(param);
//		}
//		for(Object[] vs : ars.loadDataSetValues())
//		{
//			
//		}
		return ars;
	}

	@Override
	public void buildCrossData(UIEvent event, CrossDataInfo crossDataInfo)
	{
		// TODO Auto-generated method stub
		super.buildCrossData(event, crossDataInfo);
	}
}
