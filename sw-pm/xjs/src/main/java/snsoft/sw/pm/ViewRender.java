/*#
 lib=snsoft/sw/pm.js
#*/
package snsoft.sw.pm;

import js.Date;
import js.JSCode;
import xjs.ui.CellRender;
import xjs.ui.CellRenderInfo;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p> 
 * <p>创建日期：2017年9月9日下午6:54:22</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ViewRender implements CellRender
{
	@Override
	public Object cellRender(Object value, CellRenderInfo cellInfo)
	{
		if (value == null)
		{
			return null;
		}
		Info[] infos = (Info[]) value;
		String sb = "";
		int total = 0;
		for (int i = 0; i < infos.length; i++)
		{
			Info info = infos[i];
			if (sb.length() > 0)
			{
				sb += "\n";
			}
			sb += info.bedate.format(2) + "->" + info.ledate.format(2);
			int days = (int) ((info.ledate.getTime() - info.bedate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
			if (days > 0)
			{
				sb += ":" + days;
			}
			total += days;
		}
		sb += "\n共计天数：" + total;
		return sb;
	}

	@JSCode(jscode = "false")
	public static class Info
	{
		Date	bedate;
		Date	ledate;
		String	remark;
	}
}
