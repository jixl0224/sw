package snsoft.sw.decl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.StrUtils;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.ReadDataSetFactory;
import snsoft.dx.UpdateData;
import snsoft.dx.dataset.XlsReadDataSet;
import snsoft.dx.mc.service.MakeCodeService;
import snsoft.dx.mc.service.MakeCodeService.MakeCodeParam;

/**
 * <p>标题：导入Excel数据</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * 
 * start = now().getTime()
 * localPath = ""
 * declDate = toDate(2017,5,1)
 * bean = new snsoft.sw.decl.ImportXlsData(localPath,declDate)
 * cnt = bean.importData()
 * println("cnt="+cnt+",gap="+(now().getTime()-start))
 * 
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年1月17日下午10:52:40</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ImportXlsData
{
	final String	localPath;
	final Date		declDate;

	public ImportXlsData(String localPath, Date declDate)
	{
		super();
		this.localPath = localPath;
		this.declDate = declDate;
	}

	public long importData() throws Exception
	{
		MakeCodeService codeService = SpringBeanUtils.getBeanByName("SN-CORE.MakeCodeService");
		MakeCodeParam param = new MakeCodeParam("sw_decl", "declid");
		param.setCodeLike(StrUtils.newString('_', 12));
		int n = 1000;
		long cnt = 0;
		param.setCount(n);
		try (
				//
				InputStream is = new FileInputStream(localPath);
				//
				Database db = DBUtils.newDatabaseByTable("sw_decl", true);)
		{
			XlsReadDataSet rs = ReadDataSetFactory.impl.newXlsReadDataSet(is, "导出工作表", 1);
			UpdateData updateData = new UpdateData();
			String[] codes = codeService.makeCodes(param);
			int i = 0;
			while (rs.nextRow())
			{
				cnt++;
				Map<String,Object> data = new HashMap<>();
				data.put("declid", codes[i++]);
				data.put("platid", rs.getValue(0));
				data.put("custom", rs.getValue(1));
				data.put("owner_code", rs.getValue(2));
				data.put("owner_name", rs.getValue(3));
				data.put("trade_code", rs.getValue(4));
				data.put("trade_name", rs.getValue(5));
				data.put("agent_code", rs.getValue(6));
				data.put("agent_name", rs.getValue(7));
				data.put("cnt", rs.getValue(8));
				data.put("decl_date", declDate);
				updateData.addInsert("sw_decl", data);
				if (i == n)
				{
					db.updateData(updateData, true);
					updateData.clear();
					i = 0;
					codes = codeService.makeCodes(param);
				}
			}
			if (updateData.size() > 0)
			{
				db.updateData(updateData, true);
			}
		}
		return cnt;
	}
}
