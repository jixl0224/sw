package snsoft.sw.pm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月9日上午11:59:03</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class PMView implements Serializable
{
	private static final long	serialVersionUID	= -7682351094787772405L;
	private final String		ucode;
	private final String		pcode;
	private List<Info>			infos;

	public PMView(String ucode, String pcode)
	{
		super();
		this.ucode = ucode;
		this.pcode = pcode;
	}

	public List<Info> getInfos()
	{
		if (infos == null)
		{
			infos = new ArrayList<>();
		}
		return infos;
	}

	public String getUcode()
	{
		return ucode;
	}

	public String getPcode()
	{
		return pcode;
	}

	public static class Info implements Serializable
	{
		private static final long	serialVersionUID	= -2592099660212414816L;
		final PActor				pa;

		public Info(PActor pa)
		{
			super();
			this.pa = pa;
		}

		public Date getBedate()
		{
			return pa.getBedate();
		}

		public Date getLedate()
		{
			return pa.getLedate();
		}

		public String getRemark()
		{
			return pa.getRemark();
		}
	}
}
