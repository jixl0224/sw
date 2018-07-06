package snsoft.sw.card.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.VO;

/**
 * <p>标题：筹款主表</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月7日上午9:57:15</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_cload")
public class Load extends VO
{
	private static final long	serialVersionUID	= -7456116809755645073L;
	/**主键*/
	@Id
	@Column
	@DefaultValue(value = "AutoAlloc:")
	@Min(value = 1)
	private int					lid;

	/**筹款金额*/
	@Column(scale=0)
	@Digits(integer = 10, fraction = 0)
	private BigDecimal			total;

	/**最大比例*/
	@Column(scale = 2)
	@Digits(integer = 1, fraction = 2)
	@DefaultValue(value = "0.9")
	private BigDecimal			ratio;

	/**筹款日期*/
	@Column
	@NotNull
	@DefaultValue(value = "CURDATE")
	private Date				odate;

	/**修改时间*/
	@Column
	@NotNull
	private Date				modifydate;

	/**筹款事由*/
	@Column
	private String				remark;

	@OneToMany
	@JoinColumn(name = "lid", referencedColumnName = "lid")
	private List<LoadDetail>	details;

	public int getLid()
	{
		return lid;
	}

	public void setLid(int lid)
	{
		this.lid = lid;
	}

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	public BigDecimal getRatio()
	{
		return ratio;
	}

	public void setRatio(BigDecimal ratio)
	{
		this.ratio = ratio;
	}

	public Date getOdate()
	{
		return odate;
	}

	public void setOdate(Date odate)
	{
		this.odate = odate;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public List<LoadDetail> getDetails()
	{
		if (details == null)
		{
			details = new ArrayList<>();
		}
		return details;
	}

	public void setDetails(List<LoadDetail> details)
	{
		this.details = details;
	}
}
