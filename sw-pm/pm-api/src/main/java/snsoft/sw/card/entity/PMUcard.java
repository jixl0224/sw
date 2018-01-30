package snsoft.sw.card.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.dx.VO;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月15日上午10:44:51</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_ucard")
public class PMUcard extends VO
{
	private static final long	serialVersionUID	= 5922033533977287030L;
	/**信用卡编码*/
	@Id
	@Column
	private String				ccode;
	/**用户编码*/
	@Id
	@Column
	private String				ucode;
	/**账单日*/
	@Column
	private int					sdate;
	/**还款日*/
	@Column
	private int					ddate;
	/**额度*/
	@Column
	private BigDecimal			cline;

	public String getCcode()
	{
		return ccode;
	}

	public void setCcode(String ccode)
	{
		this.ccode = ccode;
	}

	public String getUcode()
	{
		return ucode;
	}

	public void setUcode(String ucode)
	{
		this.ucode = ucode;
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
}