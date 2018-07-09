package snsoft.sw.pm.service;

import java.util.Date;
import javax.validation.constraints.NotNull;
import snsoft.api.bas.NullQueryParams;
import snsoft.api.bas.QueryParams;
import snsoft.api.service.SpringBean;
import snsoft.api.sql.SqlColumn;
import snsoft.sw.pm.entity.Actor;
import snsoft.sw.pm.entity.PActor;
import snsoft.sw.pm.entity.PMView;
import snsoft.sw.pm.entity.Project;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年9月9日上午11:58:09</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "SW-PM.PMUIService")
public interface PMUIService
{
	/**
	 * 参与者查询
	 * @param param
	 * @return
	 */
	Actor[] queryActors(NullQueryParams param);

	/**
	 * 参与者存盘
	 * @param actors
	 */
	void saveActors(Actor[] actors);

	/**
	 * 项目查询
	 * @param param
	 * @return
	 */
	Project[] queryProjects(NullQueryParams param);

	/**
	 * 项目参与者查询
	 * @param param
	 * @return
	 */
	PActor[] queryPActors(PActorParam param);

	/**
	 * 项目存盘
	 * @param actors
	 */
	void saveProjects(Project[] actors);

	/**
	 * 项目时点面板查询
	 * @param param
	 * @return
	 */
	PMView[] queryPointViews(PMPointParam param);

	/**
	 * 项目区间面板查询
	 * @param param
	 * @return
	 */
	PMView[] queryPeriodViews(PMPeriodParam param);

	public static class PActorParam extends QueryParams
	{
		private static final long	serialVersionUID	= -6076436667618271035L;
		@SqlColumn(flags = 8)
		private String				pcode;

		public String getPcode()
		{
			return pcode;
		}

		public void setPcode(String pcode)
		{
			this.pcode = pcode;
		}
	}

	public static abstract class PMViewParam extends QueryParams
	{
		private static final long	serialVersionUID	= -7245841527704008058L;
		@SqlColumn
		private String[]				ucode;
		@SqlColumn
		private String[]				pcode;
		private int					flags;

		public String[] getUcode()
		{
			return ucode;
		}

		public void setUcode(String[] ucode)
		{
			this.ucode = ucode;
		}

		public String[] getPcode()
		{
			return pcode;
		}

		public void setPcode(String[] pcode)
		{
			this.pcode = pcode;
		}

		public int getFlags()
		{
			return flags;
		}

		public boolean filterInvalid()
		{
			return (flags & 1) != 0;
		}

		public boolean ignoreWeekend()
		{
			return (flags & 2) != 0;
		}

		public void setFlags(int flags)
		{
			this.flags = flags;
		}
	}

	public static class PMPeriodParam extends PMViewParam
	{
		private static final long	serialVersionUID	= 2991851513107042126L;
		@NotNull(message = "[统计日期从]不可为空")
		private Date				datefm;
		@NotNull(message = "[统计日期到]不可为空")
		private Date				dateto;

		public Date getDatefm()
		{
			return datefm;
		}

		public void setDatefm(Date datefm)
		{
			this.datefm = datefm;
		}

		public Date getDateto()
		{
			return dateto;
		}

		public void setDateto(Date dateto)
		{
			this.dateto = dateto;
		}
	}

	public static class PMPointParam extends PMViewParam
	{
		private static final long	serialVersionUID	= 938638905346907987L;
		@NotNull(message = "[统计日期]不可为空")
		private Date				date;

		public Date getDate()
		{
			return date;
		}

		public void setDate(Date date)
		{
			this.date = date;
		}

	}
}
