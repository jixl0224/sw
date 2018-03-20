package snsoft.tst.ord;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import snsoft.bas.sheet.dft.service.DefaultValueService;
import snsoft.test.SnsoftTest;
import snsoft.tst.ord.service.BillService;
import snsoft.tst.ord.vo.Tst_bill;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月19日下午2:40:53</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class BillServiceTest extends SnsoftTest
{
	@Override
	protected Resources getResources()
	{
		return super.getResources().add("classpath:snsoft/tst/ord/BillService.xml");
	}

	@Test
	public void testQuery()
	{
		BillService service = newBean("SN-ORD.BillService");
		List<Tst_bill> bills = new ArrayList<>();
		Tst_bill bill = new Tst_bill();
		{
			DefaultValueService dftService = newBean("SN-SHEET.DefaultValueService");
			dftService.setDefaultValues(new Object[] { bill }, false);
		}
		bills.add(bill);
		service.saveBills(bills);
		BillService.BillParams params = new BillService.BillParams();
		List<Tst_bill> rs = service.queryBills(params);
	}
}
