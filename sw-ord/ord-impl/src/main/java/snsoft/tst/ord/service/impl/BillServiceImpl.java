package snsoft.tst.ord.service.impl;

import java.util.List;
import snsoft.dx.DefaultDAO;
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
 * <p>创建日期：2018年3月19日下午2:59:29</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class BillServiceImpl implements BillService
{
	@Override
	public List<Tst_bill> queryBills(BillParams params)
	{
		return new DefaultDAO<Tst_bill>().queryList(Tst_bill.class, params.buildDBQueryParams());
	}

	@Override
	public void saveBills(List<Tst_bill> bills)
	{
		new DefaultDAO<Tst_bill>().save(bills);
	}
}
