/*#
 lib=snsoft/sw/pm.js
#*/
package snsoft.sw.pm;

import xjs.dx.DataSet;
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
 * <p>创建日期：2017年9月12日上午11:01:34</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ActorRender implements CellRender
{
	@Override
	public Object cellRender(Object value, CellRenderInfo info)
	{
		int row = info.row;
		DataSet dataSet = info.getTable().dataSet;
		int cnt = 0;
		for (int i = 1; i < dataSet.columnCount; i++)
		{
			if (dataSet.getValue(i, row) != null)
			{
				cnt++;
			}
		}
		String name = info.cell.valueMap.$getAsString((String)value);
		return name + "[" + cnt + "]";
	}
}
