package sw.study.order;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <p>标题：订单明细</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月17日上午10:31:02</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
//@XmlType(name = "Good", propOrder = { "billid", "odate", "user", "fcode", "fcy", "fee", "goods" })
public class Good
{
	private int			billid;
	private int			goodid;
	private String		gcode;
	private String		unit;
	private BigDecimal	upric;
	private BigDecimal	qtc;
	private BigDecimal	fcy;

	@XmlTransient
	public int getBillid()
	{
		return billid;
	}

	public void setBillid(int billid)
	{
		this.billid = billid;
	}

	public int getGoodid()
	{
		return goodid;
	}

	public void setGoodid(int goodid)
	{
		this.goodid = goodid;
	}

	public String getGcode()
	{
		return gcode;
	}

	public void setGcode(String gcode)
	{
		this.gcode = gcode;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public BigDecimal getUpric()
	{
		return upric;
	}

	public void setUpric(BigDecimal upric)
	{
		this.upric = upric;
	}

	public BigDecimal getQtc()
	{
		return qtc;
	}

	public void setQtc(BigDecimal qtc)
	{
		this.qtc = qtc;
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
