package snsoft.sw.pm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
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
 * <p>创建日期：2017年9月9日上午11:53:58</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public abstract class PMVO extends VO
{
	private static final long	serialVersionUID	= 5241177495126175377L;
	@NotNull(message = "[启用日期]不可为空")
	@Column
	private Date				bedate;
	@Column
	private Date				ledate;
	@Column
	private String				remark;

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
}
