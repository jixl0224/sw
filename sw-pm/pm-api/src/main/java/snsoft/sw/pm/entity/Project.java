package snsoft.sw.pm.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月9日上午11:53:09</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_project")
public class Project extends PMVO
{
	private static final long	serialVersionUID	= -3039301147656174312L;
	@Id
	@Column
	private String				pcode;
	@Column
	private String				pname;
	@Column
	private Date				vpredate;
	@Column
	private String				vprepare;
	@Column
	private Date				modifydate;
	@Column
	private String				modifier;

	@JoinColumn(name = "pcode", referencedColumnName = "pcode")
	@OneToMany
	@Column
	private List<PActor>		pactors;

	public String getPcode()
	{
		return pcode;
	}

	public void setPcode(String pcode)
	{
		this.pcode = pcode;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public Date getVpredate()
	{
		return vpredate;
	}

	public void setVpredate(Date vpredate)
	{
		this.vpredate = vpredate;
	}

	public String getVprepare()
	{
		return vprepare;
	}

	public void setVprepare(String vprepare)
	{
		this.vprepare = vprepare;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}

	public String getModifier()
	{
		return modifier;
	}

	public void setModifier(String modifier)
	{
		this.modifier = modifier;
	}

	public List<PActor> getPactors()
	{
		return pactors == null ? pactors = new ArrayList<>() : pactors;
	}

	public void setPactors(List<PActor> pactors)
	{
		this.pactors = pactors;
	}
}
