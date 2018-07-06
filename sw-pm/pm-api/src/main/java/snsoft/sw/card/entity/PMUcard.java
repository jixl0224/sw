package snsoft.sw.card.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.api.dx.VO;
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
	/**羊毛*/
	@Column
	private String				ymdesc;
	/**权益*/
	@Column
	private String				rgtdesc;
	/**首刷*/
	@Column
	private String				fstdesc;
	/**积分*/
	@Column
	private String				intdesc;
	/**年费*/
	@Column
	private String				feedesc;
	/**里程*/
	@Column
	private String				mlgdesc;

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

	public String getRgtdesc()
	{
		return rgtdesc;
	}

	public void setRgtdesc(String rgtdesc)
	{
		this.rgtdesc = rgtdesc;
	}

	public String getFstdesc()
	{
		return fstdesc;
	}

	public void setFstdesc(String fstdesc)
	{
		this.fstdesc = fstdesc;
	}

	public String getIntdesc()
	{
		return intdesc;
	}

	public void setIntdesc(String intdesc)
	{
		this.intdesc = intdesc;
	}

	public String getFeedesc()
	{
		return feedesc;
	}

	public void setFeedesc(String feedesc)
	{
		this.feedesc = feedesc;
	}

	public String getYmdesc()
	{
		return ymdesc;
	}

	public void setYmdesc(String ymdesc)
	{
		this.ymdesc = ymdesc;
	}

	public String getMlgdesc()
	{
		return mlgdesc;
	}

	public void setMlgdesc(String mlgdesc)
	{
		this.mlgdesc = mlgdesc;
	}
}