package snsoft.sw.pm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import snsoft.dx.ReadDataSet;
import snsoft.dx.ReadDataSetFactory;
import snsoft.dx.dataset.ArrayReadDataSet;
import snsoft.dx.ui.CrossDataInfo;
import snsoft.ui.DefaultUIListener;
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
 * <p>创建日期：2017年9月9日下午6:37:31</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class PMViewListener extends DefaultUIListener
{
	@Override
	public ReadDataSet onDataLoaded(UIEvent event, ReadDataSet rs)
	{
		ArrayReadDataSet ars = ReadDataSetFactory.impl.toArrayReadDataSet(rs, true);
		Set<String> ucodes = new HashSet<>();
		Set<String> pcodes = new HashSet<>();
		{
			final int ucodeIdx = ars.columnAt("ucode");
			final int pcodeIdx = ars.columnAt("pcode");
			for (Object[] vs : ars.loadDataSetValues())
			{
				ucodes.add((String) vs[ucodeIdx]);
				pcodes.add((String) vs[pcodeIdx]);
			}
		}
		//
		CrossDataInfo crossDataInfo = (CrossDataInfo) rs.getClientProperty("RsConfig.crossInfo");
		{
			List<Object[]> crossRowList = new ArrayList<>();
			for (Object[] vs : crossDataInfo.crossRows)
			{
				if (ucodes.contains(vs[0]))
				{
					crossRowList.add(vs);
				}
			}
			crossDataInfo.crossRows = crossRowList.toArray(new Object[0][]);
		}
		// -----------------------------------
		{
			List<CrossDataInfo.Column> crossColumnList = new ArrayList<>();
			for (CrossDataInfo.Column sc : crossDataInfo.crossColumns)
			{
				if (pcodes.contains(sc.crossKeyValues.get("pcode")))
				{
					crossColumnList.add(sc);
				}
			}
			crossDataInfo.crossColumns = crossColumnList.toArray(new CrossDataInfo.Column[0]);
		}
		return ars;
	}
}
