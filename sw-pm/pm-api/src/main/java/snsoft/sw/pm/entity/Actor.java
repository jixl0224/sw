package snsoft.sw.pm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
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
 * <p>创建日期：2017年9月9日上午11:52:48</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_actor")
public class Actor extends PMVO
{
	private static final long	serialVersionUID	= 5185674223194717026L;
	@Id
	@Column
	private String				ucode;
	@Column
	private String				uname;
	@Column
	private int					sortidx;
	@Column
	private Date				vpredate;
	@Column
	private String				vprepare;

	public String getUcode()
	{
		return ucode;
	}

	public void setUcode(String ucode)
	{
		this.ucode = ucode;
	}

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname;
	}

	public int getSortidx()
	{
		return sortidx;
	}

	public void setSortidx(int sortidx)
	{
		this.sortidx = sortidx;
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
}
