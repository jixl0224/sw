package snsoft.sw.p2p.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.dx.DefaultDAO;
import snsoft.sw.p2p.service.P2pService;
import snsoft.sw.p2p.vo.P2p_plat;
import snsoft.sw.p2p.vo.P2p_record;
import snsoft.sw.p2p.vo.P2p_user;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月18日上午9:17:02</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service("SN-PM.P2pService")
public class P2pServiceImpl implements P2pService
{
	@Override
	public List<P2p_user> queryUsers(NullQueryParams params)
	{
		return new DefaultDAO<P2p_user>().queryList(P2p_user.class, params.buildDBQueryParams());
	}

	@Override
	public void saveUsers(List<P2p_user> users)
	{
		new DefaultDAO<P2p_user>().save(users);
	}

	@Override
	public List<P2p_plat> queryPlats(NullQueryParams params)
	{
		return new DefaultDAO<P2p_plat>().queryList(P2p_plat.class, params.buildDBQueryParams());
	}

	@Override
	public void savePlats(List<P2p_plat> plats)
	{
		new DefaultDAO<P2p_plat>().save(plats);
	}

	@Override
	public List<P2p_record> queryRecords(RecordParams params)
	{
		return new DefaultDAO<P2p_record>().queryList(P2p_record.class, params.buildDBQueryParams());
	}

	@Override
	public void saveRecords(List<P2p_record> plats)
	{
		new DefaultDAO<P2p_record>().save(plats);
	}
}
