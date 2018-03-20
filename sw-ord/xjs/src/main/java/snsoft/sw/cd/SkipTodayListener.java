/*#
 lib=snsoft/sw/cd.js
#*/
package snsoft.sw.cd;

import xjs.dx.DataSet;
import xjs.dx.DataSetEvent;
import xjs.table.DefaultListener;
import xjs.table.Table;

/**
 * <p>标题：聚焦当前日期列监听类</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月15日上午9:26:02</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class SkipTodayListener extends DefaultListener
{
	Table table;

	@Override
	public void addTableNotify(Table table)
	{
		this.table = table;
	}

	@Override
	public void dataLoaded(DataSet dataSet, DataSetEvent e)
	{
		super.dataLoaded(dataSet, e);
	}
}
