package snsoft.sw.card;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import snsoft.api.bas.NullQueryParams;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.DateUtils;
import snsoft.dx.codedata.CodeData;
import snsoft.dx.codedata.CodeDataService;
import snsoft.sw.card.entity.PMUcard;
import snsoft.sw.card.service.CreditUIService;
import snsoft.ui.DefaultUIListener;
import snsoft.ui.UIComponent;
import snsoft.ui.UIEvent;

/**
 * <p>标题：日期交叉列</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月10日上午10:33:04</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class OdateCrossListener extends DefaultUIListener
{
	@Override
	public Object getCodeNameMap(UIEvent event, UIComponent component)
	{
		if ("odate".equals(component.uiname))
		{
			Date odate = DateUtils.toDate(event.getParameter("odate"));
			List<String[]> list = new ArrayList<>();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(odate);
			int days = calendar.getActualMaximum(GregorianCalendar.DATE);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			int year = DateUtils.getDateYear(odate);
			int month = DateUtils.getDateMonth(odate);
			for (int i = 0; i < days; i++)
			{
				String date = dateFormat.format(DateUtils.toDate(year, month, i + 1));
				list.add(new String[] { date, "" + (i + 1) });
			}
			return list.toArray(new String[list.size()][]);
		} else if ("ccode".equals(component.uiname))
		{
			CreditUIService service = SpringBeanUtils.getBeanByName("SN-PM.CreditUIService");
			PMUcard[] cards = service.queryPMUcard(new NullQueryParams());
			List<String[]> list = new ArrayList<>();
			CodeData codeData = CodeDataService.impl.loadCodeData("SW-CD.ccard", null);
			for (PMUcard card : cards)
			{
				list.add(new String[] { card.getCcode(), (String) codeData.get(card.getCcode()) });
			}
			return list.toArray(new String[list.size()][]);
		}
		return super.getCodeNameMap(event, component);
	}
}
