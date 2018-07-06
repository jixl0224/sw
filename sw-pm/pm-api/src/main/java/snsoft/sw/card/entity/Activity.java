package snsoft.sw.card.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.VO;

/**
 * <p>标题：信用卡活动</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年4月6日下午3:50:01</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_activity")
public class Activity extends VO
{
	private static final long	serialVersionUID	= 4759153778734387673L;
	/**主键*/
	@Id
	@Column
	@DefaultValue(value = "AutoAlloc:")
	private int					id;

	/**用户*/
	@Column
	@DefaultValue(value = "%EnvParam(USERCODE)")
	private String				ucode;

	/**信用卡*/
	@Column
	private String				ccode;

	/**生效日期*/
	@Column
	@DefaultValue(value = "CURDATE")
	private Date				bedate;

	/**失效日期*/
	@Column
	private Date				ledate;

	/**说明*/
	@Column
	private String				remark;

	/**标识*/
	@Column
	private int					flags;

	/**状态*/
	@Column
	@DefaultValue("N")
	private String				status;

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

	public String getCcode()
	{
		return ccode;
	}

	public void setCcode(String ccode)
	{
		this.ccode = ccode;
	}

	public Date getBedate()
	{
		return bedate;
	}

	public void setBedate(Date bedate)
	{
		this.bedate = bedate;
	}

	public Date getLedate()
	{
		return ledate;
	}

	public void setLedate(Date ledate)
	{
		this.ledate = ledate;
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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
