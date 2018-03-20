package snsoft.tst.ord.vo;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月20日下午2:12:47</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.dx.VO;
@Table(name = "tst_good")
public class Tst_good extends VO
{
	private static final long	serialVersionUID	= -337974172754113624L;
	/**主键*/
	@Id
	@Column
	private String				billgicode;
	/**外键*/
	@Column
	private String				billicode;
	/**日期*/
	@Column
	private String				gcode;
	/**币种*/
	@Column
	private BigDecimal			qtc;
	/**金额*/
	@Column
	private BigDecimal			upric;
	/**备注*/
	@Column
	private BigDecimal			fcy;

	public String getBillgicode()
	{
		return billgicode;
	}

	public void setBillgicode(String billgicode)
	{
		this.billgicode = billgicode;
	}

	public String getBillicode()
	{
		return billicode;
	}

	public void setBillicode(String billicode)
	{
		this.billicode = billicode;
	}

	public String getGcode()
	{
		return gcode;
	}

	public void setGcode(String gcode)
	{
		this.gcode = gcode;
	}

	public BigDecimal getQtc()
	{
		return qtc;
	}

	public void setQtc(BigDecimal qtc)
	{
		this.qtc = qtc;
	}

	public BigDecimal getUpric()
	{
		return upric;
	}

	public void setUpric(BigDecimal upric)
	{
		this.upric = upric;
	}

	public BigDecimal getFcy()
	{
		return fcy;
	}

	public void setFcy(BigDecimal fcy)
	{
		this.fcy = fcy;
	}
}