package snsoft.sw.card.entity;

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
 * <p>创建日期：2017年9月15日上午10:43:31</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_ccard")
public class PMCcard extends VO
{
	private static final long	serialVersionUID	= -2528147117154892348L;
	/**编码*/
	@Id
	@Column
	private String				code;
	/**名称*/
	@Column
	private String				name;
	/**描述*/
	@Column
	private String				remark;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
