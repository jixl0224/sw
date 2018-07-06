package snsoft.sw.p2p.vo;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月18日上午7:42:06</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.api.dx.VO;
@Table(name = "p2p_record")
public class P2p_record extends VO
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4785834997378489163L;
	/**主键*/
	@Id
	@Column
	private int					id;
	/**用户*/
	@Column
	private String				ucode;
	/**平台*/
	@Column
	private String				pcode;
	/**起购日期*/
	@Column
	private Date				bedate;
	/**购买金额*/
	@Column
	private BigDecimal			fcy1;
	/**优惠额*/
	@Column
	private BigDecimal			fcy2;
	/**返现*/
	@Column
	private BigDecimal			fcy3;
	/**预期收益*/
	@Column
	private BigDecimal			fcy4;
	/**实际收益*/
	@Column
	private BigDecimal			fcy5;
	/**回购日期*/
	@Column
	private Date				ledate;
	/**产品说明*/
	@Column
	private String				prddesc;
	/**年化*/
	@Column
	private BigDecimal			ratio1;
	/**说明*/
	@Column
	private String				remark;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUcode()
	{
		return ucode;
	}

	public void setUcode(String ucode)
	{
		this.ucode = ucode;
	}

	public String getPcode()
	{
		return pcode;
	}

	public void setPcode(String pcode)
	{
		this.pcode = pcode;
	}

	public Date getBedate()
	{
		return bedate;
	}

	public void setBedate(Date bedate)
	{
		this.bedate = bedate;
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

	public BigDecimal getFcy5()
	{
		return fcy5;
	}

	public void setFcy5(BigDecimal fcy5)
	{
		this.fcy5 = fcy5;
	}

	public Date getLedate()
	{
		return ledate;
	}

	public void setLedate(Date ledate)
	{
		this.ledate = ledate;
	}

	public String getPrddesc()
	{
		return prddesc;
	}

	public void setPrddesc(String prddesc)
	{
		this.prddesc = prddesc;
	}

	public BigDecimal getRatio1()
	{
		return ratio1;
	}

	public void setRatio1(BigDecimal ratio1)
	{
		this.ratio1 = ratio1;
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