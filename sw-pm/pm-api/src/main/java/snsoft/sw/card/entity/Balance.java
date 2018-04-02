package snsoft.sw.card.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import snsoft.bas.sheet.dft.annotation.DefaultValue;
import snsoft.dx.VO;

/**
 * <p>标题：收支</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * 消费+还款
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月10日下午1:49:18</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Balance extends VO
{
	private static final long	serialVersionUID	= 997864853596392809L;
	/**信用卡*/
	@Id
	@Column
	@DefaultValue("%EnvParam(USERCODE)")
	private String				ucode;
	/**信用卡*/
	@Id
	@Column
	private String				ccode;
	/**消费日期*/
	@Id
	@Column
	private String				odate;
	/**金额*/
	@Column
	private BigDecimal			fcy;

	public String getUcode()
	{
		return ucode;
	}

	public void setUcode(String ucode)
	{
		this.ucode = ucode;
	}

	public String getCcode()
	{
		return ccode;
	}

	public void setCcode(String ccode)
	{
		this.ccode = ccode;
	}

	public String getOdate()
	{
		return odate;
	}

	public void setOdate(String odate)
	{
		this.odate = odate;
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
