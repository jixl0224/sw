/*#
 lib=snsoft/sw/cd.js
#*/
package snsoft.sw.cd;

import js.FunctionCall;
import js.Window;
import xjs.core.RInvoke;
import xjs.table.DefaultTableListener;
import xjs.table.Table;
import xjs.ui.util.UIUtil;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午11:05:52</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class LoadListener extends DefaultTableListener
{
	private Table table;

	@Override
	public void addTableNotify(Table table)
	{
		this.table = table;
	}

	void oncmd_loadCards(Table table)
	{
		if (table.dataSet.getRowCount() == 0)
		{
			loadCards();
		} else
		{
			UIUtil.showYesNoDialog("询问", "重新加载将会删除已经存在的数据，确认重新加载吗？", new FunctionCall($getAsFunction("onLoadCards"), this), null);
		}
	}

	void onLoadCards(Window w, String cmd)
	{
		if ("yes" == cmd)
		{
			loadCards();
		}
	}

	void loadCards()
	{

		int lid = table.masterTable.dataSet.getValueAsInt("lid");
		LoadService service = RInvoke.newBean(LoadService.class);
		service.loadCards(lid);
		table.masterTable.dataSet.refreshDetail(table.dataSet, null);
	}

	void oncmd_calcCards(Table table)
	{
		table.masterTable.saveChanges();
		int lid = table.masterTable.dataSet.getValueAsInt("lid");
		LoadService service = RInvoke.newBean(LoadService.class);
		service.calcCards(lid);
		table.masterTable.dataSet.refreshDetail(table.dataSet, null);
	}
}
