package snsoft.sw.decl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import snsoft.commons.util.StrUtils;
import snsoft.dx.DataColumn;
import snsoft.dx.DataRow;
import snsoft.dx.DataTable;
import snsoft.dx.ReadDataSet;
import snsoft.dx.ReadDataSetFactory;
import snsoft.dx.dataset.ArrayReadDataSet;
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
 * <p>创建日期：2018年1月18日下午1:18:33</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class YMCrossListener extends DefaultUIListener
{
	@Override
	public ReadDataSet loadData(UIEvent event)
	{
		ReadDataSet rs = event.getDatabase().queryReadDataSet(event.sql);
		ArrayReadDataSet ars = ReadDataSetFactory.impl.toArrayReadDataSet(rs, true);
		int idx = ars.columnAt("ym");
		Set<String> yms = new TreeSet<>();
		for (Object[] vs : ars.getValues())
		{
			yms.add((String) vs[idx]);
		}
		event.parameter.put("yms", yms);
		return ars;
	}

	@Override
	public Object getCodeNameMap(UIEvent event, UIComponent component)
	{
		if ("ym".equals(component.uiname))
		{
			Set<String> yms = (Set<String>) event.getParameter("yms");
			List<String[]> list = new ArrayList<>();
			for (String ym : yms)
			{
				list.add(new String[] { ym, ym });
			}
			return list.toArray(new String[list.size()][]);
		}
		return super.getCodeNameMap(event, component);
	}

	@Override
	public void onBuildPrintExportExtColumn(UIEvent event, ReadDataSet rs, List<DataColumn> extColumns)
	{
		DataTable dataSet = (DataTable) rs;
		List<DataRow> rows = dataSet.getRows();
		List<Integer> crossCols = new ArrayList<>();
		for (int i = 0; i < dataSet.getColumnCount(); i++)
		{
			DataColumn dc = dataSet.getColumn(i);
			if (dc.columnName.startsWith("$CRCOL$"))
			{
				crossCols.add(i);
			}
		}
		int sumIdx = dataSet.columnAt("sum");
		for (DataRow row : rows)
		{
			int sum = 0;
			for (Integer i : crossCols)
			{
				sum += StrUtils.obj2int(row.values[i]);
			}
			row.values[sumIdx] = sum;
		}
		dataSet.setSort(new int[] { -(sumIdx + 1) });
	}
}
