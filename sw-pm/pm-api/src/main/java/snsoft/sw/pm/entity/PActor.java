package snsoft.sw.pm.entity;

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
 * <p>创建日期：2017年9月9日上午11:53:47</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "pm_pactors")
public class PActor extends PMVO
{
	private static final long	serialVersionUID	= 6486769355016242764L;
	@Id
	@Column
	private String				pcode;
	@Id
	@Column
	private int					sortidx;
	@Column
	private String				ucode;

	public String getPcode()
	{
		return pcode;
	}

	public void setPcode(String pcode)
	{
		this.pcode = pcode;
	}

	public int getSortidx()
	{
		return sortidx;
	}

	public void setSortidx(int sortidx)
	{
		this.sortidx = sortidx;
	}

	public String getUcode()
	{
		return ucode;
	}

	public void setUcode(String ucode)
	{
		this.ucode = ucode;
	}
}
