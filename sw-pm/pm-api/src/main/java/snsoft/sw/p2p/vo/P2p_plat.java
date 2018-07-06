package snsoft.sw.p2p.vo;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月18日上午7:41:48</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.api.dx.VO;
@Table(name = "p2p_plat")
public class P2p_plat extends VO
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7773946687297560619L;
	/**平台编码*/
	@Id
	@Column
	private String				pcode;
	/**平台名称*/
	@Column
	private String				pname;
	/**平台类型*/
	@Column
	private String				ptype;
	/**相关说明*/
	@Column
	private String				remark;

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

	public String getPtype()
	{
		return ptype;
	}

	public void setPtype(String ptype)
	{
		this.ptype = ptype;
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