package snsoft.sw.card.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.VO;
import snsoft.api.validation.annotation.CodeTable;

/**
 * <p>标题：筹款明细</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午9:58:49</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_cloadg")
public class LoadDetail extends VO
{
	private static final long	serialVersionUID	= -2435362798376606145L;
	/**主键*/
	@Id
	@Column
	@DefaultValue(value = "AutoAlloc:")
	@Min(value = 1)
	private int					gid;

	/**外键*/
	@Column
	@NotNull
	private int					lid;

	/**信用卡编码*/
	@Column
	@CodeTable(value = "SW-CD.ccard")
	private String				ccode;

	/**账单日*/
	@Column
	private int					sdate;

	/**额度*/
	@Column
	private BigDecimal			cline;

	/**未还款*/
	@Column(scale = 2)
	private BigDecimal			r1;

	/**未出账*/
	@Column(scale = 2)
	private BigDecimal			r2;

	/**剩余*/
	@Column(scale = 2)
	private BigDecimal			rv;

	/**无息天数*/
	@Column
	private int					cdays;

	/**筹款金额*/
	@Column(scale = 0)
	private BigDecimal			cv;

	public int getGid()
	{
		return gid;
	}

	public void setGid(int gid)
	{
		this.gid = gid;
	}

	public int getLid()
	{
		return lid;
	}

	public void setLid(int lid)
	{
		this.lid = lid;
	}

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

	public BigDecimal getCline()
	{
		return cline;
	}

	public void setCline(BigDecimal cline)
	{
		this.cline = cline;
	}

	public BigDecimal getR1()
	{
		return r1;
	}

	public void setR1(BigDecimal r1)
	{
		this.r1 = r1;
	}

	public BigDecimal getR2()
	{
		return r2;
	}

	public void setR2(BigDecimal r2)
	{
		this.r2 = r2;
	}

	public BigDecimal getRv()
	{
		return rv;
	}

	public void setRv(BigDecimal rv)
	{
		this.rv = rv;
	}

	public int getCdays()
	{
		return cdays;
	}

	public void setCdays(int cdays)
	{
		this.cdays = cdays;
	}

	public BigDecimal getCv()
	{
		return cv;
	}

	public void setCv(BigDecimal cv)
	{
		this.cv = cv;
	}
}
