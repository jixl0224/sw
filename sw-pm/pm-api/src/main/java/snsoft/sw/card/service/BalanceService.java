package snsoft.sw.card.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import snsoft.bas.service.QueryParams;
import snsoft.commons.spring.SpringBean;
import snsoft.sw.card.entity.Consume;
import snsoft.sw.card.entity.Repayment;

/**
 * <p>标题：消费/还款服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月11日上午8:48:55</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "SN-PM.BalanceService")
public interface BalanceService
{
	List<Consume> queryConsume(BalanceParams params);

	List<Repayment> queryRepayment(BalanceParams params);

	List<Bill> queryBill(BalanceParams params);

	class Bill
	{
		// 信用卡
		private String		ccode;
		// 账单日
		private int			sdate;
		// 还款日
		private int			ddate;
		/**
		 * 额度
		 */
		private BigDecimal	cline;
		/**
		 * 未还款：从上个账单日的出账金额
		 */
		private BigDecimal	fcy1;
		/**
		 * 未入账：从上个账单日至今合计
		 */
		private BigDecimal	fcy2;
		/**
		 * 已还款
		 */
		private BigDecimal	fcy3;
		/**
		 * 剩余额度
		 */
		private BigDecimal	fcy4;
		/**
		 * 消费占比
		 */
		private BigDecimal	ratio1;
		/**
		 * 账单日天数
		 */
		private int			sdays;
		/**
		 * 还款日天数
		 */
		private int			ddays;
		/**
		 * 日均建议
		 */
		private BigDecimal	fcy5;

		public String getCcode()
		{
			return ccode;
		}

		public void setCcode(String ccode)
		{
			this.ccode = ccode;
		}

		public int getSdate()
		{
			return sdate;
		}

		public void setSdate(int sdate)
		{
			this.sdate = sdate;
		}

		public int getDdate()
		{
			return ddate;
		}

		public void setDdate(int ddate)
		{
			this.ddate = ddate;
		}

		public BigDecimal getCline()
		{
			return cline;
		}

		public void setCline(BigDecimal cline)
		{
			this.cline = cline;
		}

		public BigDecimal getFcy1()
		{
			return fcy1;
		}

		public void setFcy1(BigDecimal fcy1)
		{
			this.fcy1 = fcy1;
		}

		public BigDecimal getFcy2()
		{
			return fcy2;
		}

		public void setFcy2(BigDecimal fcy2)
		{
			this.fcy2 = fcy2;
		}

		public BigDecimal getFcy3()
		{
			return fcy3;
		}

		public void setFcy3(BigDecimal fcy3)
		{
			this.fcy3 = fcy3;
		}

		public BigDecimal getFcy4()
		{
			return fcy4;
		}

		public void setFcy4(BigDecimal fcy4)
		{
			this.fcy4 = fcy4;
		}

		public BigDecimal getRatio1()
		{
			return ratio1;
		}

		public void setRatio1(BigDecimal ratio1)
		{
			this.ratio1 = ratio1;
		}

		public int getSdays()
		{
			return sdays;
		}

		public void setSdays(int sdays)
		{
			this.sdays = sdays;
		}

		public int getDdays()
		{
			return ddays;
		}

		public void setDdays(int ddays)
		{
			this.ddays = ddays;
		}

		public BigDecimal getFcy5()
		{
			return fcy5;
		}

		public void setFcy5(BigDecimal fcy5)
		{
			this.fcy5 = fcy5;
		}
	}

	class BalanceParams extends QueryParams
	{
		private static final long	serialVersionUID	= 2803096142335091328L;
		private Date				odate;

		public Date getOdate()
		{
			return odate;
		}

		public void setOdate(Date odate)
		{
			this.odate = odate;
		}
	}
}
