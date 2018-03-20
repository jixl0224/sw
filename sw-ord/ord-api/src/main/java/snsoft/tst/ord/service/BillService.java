package snsoft.tst.ord.service;

import java.util.Date;
import java.util.List;
import snsoft.bas.service.QueryParams;
import snsoft.commons.annotation.AuthParam;
import snsoft.commons.spring.SpringBean;
import snsoft.sql.SqlExpr;
import snsoft.sql.annotation.SqlColumn;
import snsoft.tst.ord.vo.Tst_bill;
import snsoft.tst.ord.vo.Tst_good;

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
	@AuthParam(sheetCode = "ORD.Bill", opids = { "R", "C" })
	List<Tst_bill> queryBills(BillParams params);

	@AuthParam(sheetCode = "ORD.Bill", opids = { "C" })
	void saveBills(List<Tst_bill> bills);

	List<Tst_good> queryGoods(GoodParams params);

	class GoodParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4936639411550758742L;
		@SqlColumn(flags = 8)
		private String				billicode;

		public String getBillicode()
		{
			return billicode;
		}

		public void setBillicode(String billicode)
		{
			this.billicode = billicode;
		}

	}

	class BillParams extends QueryParams
	{
		private static final long	serialVersionUID	= -2699568551019140644L;

		@SqlColumn(column = "odate", sqlop = SqlExpr.GE)
		private Date				odatefm;

		@SqlColumn(column = "odate", sqlop = SqlExpr.LE)
		private Date				odateto;

		@SqlColumn(sqlop = SqlExpr.IN)
		private String[]			fcode;

		@SqlColumn(sqlop = SqlExpr.LIKE)
		private String				remark;

		public Date getOdatefm()
		{
			return odatefm;
		}

		public void setOdatefm(Date odatefm)
		{
			this.odatefm = odatefm;
		}

		public Date getOdateto()
		{
			return odateto;
		}

		public void setOdateto(Date odateto)
		{
			this.odateto = odateto;
		}

		public String[] getFcode()
		{
			return fcode;
		}

		public void setFcode(String[] fcode)
		{
			this.fcode = fcode;
		}

		public String getRemark()
		{
			return remark;
		}

		public void setRemark(String remark)
		{
			this.remark = remark;
		}
	}
}
