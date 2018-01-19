/*#
 lib=snsoft/sw/decl.js
#*/
package snsoft.sw.decl;

import xjs.dx.DataSet;
import xjs.dx.DataSetEvent;
import xjs.dx.DataSetRow;
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
 * <p>创建日期：2018年1月18日下午1:50:43</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class YMSumListener
{
	public void dataLoaded(DataSet dataSet, DataSetEvent e)
	{
		DataSetRow[] rows = dataSet.getRows();
		if (rows.length == 0)
		{
			return;
		}
		int sumIdx = dataSet.columnAt("sum");
		for (int i = 0; i < rows.length; i++)
		{
			DataSetRow row = rows[i];
			int sum = 0;
			for (int c = 0; c < dataSet.getColumns().length; c++)
			{
				if (dataSet.getColumn(c).name.indexOf("$CRCOL$") == 0)
				{
					sum += (int) rows[i].get(c);
				}
			}
			row.$set(sumIdx, sum);
		}
		//
		dataSet.setSort(new int[] { -(sumIdx + 1) });
		dataSet.gotoRow(0);
		//
		Table table = dataSet.getListener(Table.class);
		table.aggregate.reAgg();
	}
}
