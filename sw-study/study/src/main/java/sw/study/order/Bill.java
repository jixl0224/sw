package sw.study.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>标题：订单主表</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月17日上午10:30:57</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Bill")
public class Bill
{
	private int			billid;
	private Date		odate;
	private String		user;
	private String		fcode;
	private BigDecimal	fcy;
	private BigDecimal	fee;
	private List<Good>	goods;

	public int getBillid()
	{
		return billid;
	}

	public void setBillid(int billid)
	{
		this.billid = billid;
	}

	public Date getOdate()
	{
		return odate;
	}

	public void setOdate(Date odate)
	{
		this.odate = odate;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getFcode()
	{
		return fcode;
	}

	public void setFcode(String fcode)
	{
		this.fcode = fcode;
	}

	public BigDecimal getFcy()
	{
		return fcy;
	}

	public void setFcy(BigDecimal fcy)
	{
		this.fcy = fcy;
	}

	public BigDecimal getFee()
	{
		return fee;
	}

	public void setFee(BigDecimal fee)
	{
		this.fee = fee;
	}

	public List<Good> getGoods()
	{
		if (goods == null)
		{
			goods = new ArrayList<>();
		}
		return goods;
	}

	public void setGoods(List<Good> goods)
	{
		this.goods = goods;
	}
}
