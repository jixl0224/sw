package snsoft.tst.ord.service;

import java.util.List;
import snsoft.bas.service.QueryParams;
import snsoft.commons.spring.SpringBean;
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
 * <p>创建日期：2018年3月19日下午2:37:58</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "SN-ORD.BillService")
public interface BillService
{
	List<Tst_bill> queryBills(BillParams params);

	void saveBills(List<Tst_bill> bills);

	class BillParams extends QueryParams
	{

		private static final long serialVersionUID = -2699568551019140644L;

	}
}
