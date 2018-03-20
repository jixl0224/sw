/*#
 lib=snsoft/sw/pm.js
#*/
package snsoft.sw.pm;

import xjs.dx.DataSet;
import xjs.dx.DataSetEvent;
import xjs.dx.DefaultDataSetListener;
import xjs.table.Table;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月11日下午1:35:01</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ViewBottomValue extends DefaultDataSetListener
{
	@Override
	public void dataLoaded(DataSet dataSet, DataSetEvent e)
	{
		Table table = dataSet.getListener(Table.class);
		for (int i = 0; i < dataSet.columnCount; i++)
		{
			String name = dataSet.getColumn(i).name;
			if (name.indexOf("$CRCOL") != 0)
			{
				continue;
			}
			int num = 0;
			for (int r = 0; r < dataSet.getRowCount(); r++)
			{
				if (dataSet.getValue(i, r) != null)
				{
					num++;
				}
			}
			table.getColumn(name).setBottomValue("人数：" + num);
		}
	}
}
