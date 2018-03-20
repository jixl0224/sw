package snsoft.tst.ord.vo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import snsoft.bas.sheet.dft.annotation.DefaultValue;
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
 * <p>创建日期：2018年3月19日下午2:34:12</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "tst_bill")
public class Tst_bill extends VO
{
	private static final long	serialVersionUID	= -1882803042721614631L;
	/**主键*/
	@Id
	@Column
	@DefaultValue("AutoAlloc:INNER_____")
	private String				billicode;

	/**外码*/
	@Column
	@DefaultValue("AutoAlloc:OUTTER_____")
	private String				billcode;

	/**日期*/
	@Column
	@DefaultValue("CURDATE")
	private Date				odate;

	/**币种*/
	@Column
	private String				fcode;

	/**金额*/
	@Column(scale=2)
	
	private BigDecimal			fcy;

	/**备注*/
	@Column
	@Length(max=8)
	private String				remark;

	/**标识*/
	@Column
	private int					flags;

	public String getBillicode()
	{
		return billicode;
	}

	public void setBillicode(String billicode)
	{
		this.billicode = billicode;
	}

	public String getBillcode()
	{
		return billcode;
	}

	public void setBillcode(String billcode)
	{
		this.billcode = billcode;
	}

	public Date getOdate()
	{
		return odate;
	}

	public void setOdate(Date odate)
	{
		this.odate = odate;
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

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public int getFlags()
	{
		return flags;
	}

	public void setFlags(int flags)
	{
		this.flags = flags;
	}
}
