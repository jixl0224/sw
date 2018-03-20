/*#
 lib=snsoft/sw/cd.js
#*/
package snsoft.sw.cd;

import xjs.dx.DataSet;
import xjs.table.ColumnValueAccess;
import xjs.table.TableColumn;

/**
 * <p>标题：当天消费合计值</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月15日上午11:07:00</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class SumValueAccess extends ColumnValueAccess
{
	@Override
	public Object getValue(DataSet dataSet, TableColumn column, int row)
	{
		return dataSet.getValue(column.name, row);
	}

	@Override
	public void setValue(DataSet dataSet, TableColumn column, Object value, ColumnValueAccessEvent e)
	{
		dataSet.setValue(column.name, eval((String) value));
	}
}
