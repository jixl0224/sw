package snsoft.sw.card.entity;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月15日下午5:40:14</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CardView implements Serializable
{
	private static final long	serialVersionUID	= 5873280659150989885L;
	@Column
	private String				ccode;
	@Column
	private int					odate;
	@Column
	private String				datetype;

	public String getCcode()
	{
		return ccode;
	}

	public void setCcode(String ccode)
	{
		this.ccode = ccode;
	}

	public int getOdate()
	{
		return odate;
	}

	public void setOdate(int date)
	{
		this.odate = date;
	}

	public String getDatetype()
	{
		return datetype;
	}

	public void setDatetype(String datetype)
	{
		this.datetype = datetype;
	}
}
