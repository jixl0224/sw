package snsoft.sw.decl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.util.StrUtils;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.UpdateData;
import snsoft.dx.codedata.CodeDataService;
import snsoft.dx.mc.service.MakeCodeService;
import snsoft.dx.mc.service.MakeCodeService.BatchCodeMaker;
import snsoft.dx.mc.service.MakeCodeService.MakeCodeParam;
import snsoft.tools.bigxls.BigExcelFactory;
import snsoft.tools.bigxls.BigExcelReader;

/**
 * <p>标题：导入Excel数据</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * 
start = now().getTime()
localPath = "D:/Seafile/项目文档/001 数据中心/02 标准版/003 全国/201711.xlsx"
declDate = toDate(2017,11,1)
bean = new snsoft.sw.decl.ImportXlsData(localPath,declDate)
cnt = bean.importData()
println("cnt="+cnt+",gap="+(now().getTime()-start))
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
		AtomicLong counter = new AtomicLong();
		param.setCount(n);
		try (
				//
				InputStream is = new FileInputStream(localPath);
				//
				Database db = DBUtils.newDatabaseByTable("sw_decl", true);)
		{

			UpdateData updateData = new UpdateData();
			List<String> codes = new ArrayList<>(Arrays.asList(codeService.makeCodes(param)));
			BigExcelReader reader = BigExcelFactory.fac.newBigExcelReader(is, "导出工作表", 1);
			long lines = reader.read(row -> {
				int int1 = row.getInt(8);
				if (int1 == 0)
				{
					return;
				}
				long cnt = counter.incrementAndGet();
				Map<String,Object> data = new HashMap<>();
				data.put("declid", codes.get(updateData.countUpdate()));
				data.put("platid", row.getValue(0));
				data.put("custom", row.getValue(1));
				data.put("owner_code", row.getValue(2));
				data.put("owner_name", row.getValue(3));
				data.put("trade_code", row.getValue(4));
				data.put("trade_name", row.getValue(5));
				data.put("agent_code", row.getValue(6));
				data.put("agent_name", row.getValue(7));
				data.put("cnt", int1);
				data.put("decl_date", declDate);
				updateData.addInsert("sw_decl", data);
				if (cnt % n == 0)
				{
					db.updateData(updateData, true);
					updateData.clear();
					codes.clear();
					codes.addAll(Arrays.asList(codeService.makeCodes(param)));
				}
			});
			if (updateData.countUpdate() > 0)
			{
				db.updateData(updateData, true);
			}
			System.out.println("total lines =" + lines);
		}
		return counter.get();
	}

	/**
	 * 导入货值
	 */
	public long importCurr() throws Exception
	{
		MakeCodeService codeService = SpringBeanUtils.getBeanByName("SN-CORE.MakeCodeService");
		MakeCodeParam param = new MakeCodeParam("sw_curr", "id");
		int n = 1000;
		AtomicLong counter = new AtomicLong();
		param.setCount(n);
		try (
				//
				InputStream is = new FileInputStream(localPath);
				//
				Database db = DBUtils.newDatabaseByTable("sw_curr", true);)
		{
			UpdateData updateData = new UpdateData();
			Map<String,String> nameMap = new HashMap<>();
			{
				Object[][] values = CodeDataService.impl.loadCodeData("DT_SW.curr", null).getValues();
				for(Object[] vs : values)
				{
					nameMap.put((String)vs[1], (String)vs[0]);
				}
			}
			BatchCodeMaker codeMaker = codeService.batchMacker(0, n, param);
			BigExcelReader reader = BigExcelFactory.fac.newBigExcelReader(is, "导出工作表", 1);
			long lines = reader.read(row -> {
				int int1 = row.getInt(5);
				if (int1 == 0)
				{
					return;
				}
				long cnt = counter.incrementAndGet();
				Map<String,Object> data = new HashMap<>();
				data.put("id", codeMaker.nextLong());
				data.put("platid", row.getValue(0));
				data.put("owner_code", row.getValue(1));
				data.put("owner_name", row.getValue(2));
				data.put("curr", row.getValue(3));
				data.put("cnt", row.getValue(4));
				data.put("decl_total", row.getValue(5));
				updateData.addInsert("sw_curr", data);
				if (cnt % n == 0)
				{
					db.updateData(updateData, true);
					updateData.clear();
				}
			});
			if (updateData.countUpdate() > 0)
			{
				db.updateData(updateData, true);
			}
			System.out.println("total lines =" + lines);
		}
		return counter.get();
	}
}
