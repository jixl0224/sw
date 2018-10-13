package snsoft.sw.p2p.service;

import java.util.Date;
import java.util.List;
import snsoft.api.bas.NullQueryParams;
import snsoft.api.bas.QueryParams;
import snsoft.api.sql.SqlColumn;
import snsoft.api.sql.SqlExpression;
import snsoft.sw.p2p.vo.P2p_plat;
import snsoft.sw.p2p.vo.P2p_record;
import snsoft.sw.p2p.vo.P2p_user;

/**
 * <p>标题：P2P服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月18日上午7:44:58</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface P2pService
{
	List<P2p_user> queryUsers(NullQueryParams params);

	void saveUsers(List<P2p_user> users);

	List<P2p_plat> queryPlats(NullQueryParams params);

	void savePlats(List<P2p_plat> plats);

	List<P2p_record> queryRecords(RecordParams params);

	void saveRecords(List<P2p_record> plats);

	class RecordParams extends QueryParams
	{
		private static final long	serialVersionUID	= 1706086363152650755L;

		@SqlColumn
		private String				ucode;

		@SqlColumn
		private String				pcode;

		@SqlColumn(sqlop = SqlExpression.GE, column = "ledate")
		private Date				ledatefm;

		public String getUcode()
		{
			return ucode;
		}

		public void setUcode(String ucode)
		{
			this.ucode = ucode;
		}

		public String getPcode()
		{
			return pcode;
		}

		public void setPcode(String pcode)
		{
			this.pcode = pcode;
		}

		public Date getLedatefm()
		{
			return ledatefm;
		}

		public void setLedatefm(Date ledatefm)
		{
			this.ledatefm = ledatefm;
		}
	}
}
