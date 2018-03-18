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
 * <p>创建日期：2018年3月18日上午7:41:06</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import snsoft.dx.VO;
@Table(name = "p2p_user")
public class P2p_user extends VO
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6056499992354691964L;
	/**用户编码*/
	@Id
	@Column
	private String				ucode;
	/**用户名称*/
	@Column
	private String				uname;
	/**身份证号*/
	@Column
	private String				idno;
	/**有效期*/
	@Column
	private Date				iddate;
	/**手机号*/
	@Column
	private String				mobile;

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

	public String getIdno()
	{
		return idno;
	}

	public void setIdno(String idno)
	{
		this.idno = idno;
	}

	public Date getIddate()
	{
		return iddate;
	}

	public void setIddate(Date iddate)
	{
		this.iddate = iddate;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
}
