package snsoft.sw.p2p.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import snsoft.api.bas.NullQueryParams;
import snsoft.dx.DAO;
import snsoft.dx.util.ParamUtils;
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
		return DAO.newInstance(P2p_user.class).queryList(ParamUtils.buildDBQueryParams(params));
	}

	@Override
	public void saveUsers(List<P2p_user> users)
	{
		DAO.newInstance(P2p_user.class).save(users);
	}

	@Override
	public List<P2p_plat> queryPlats(NullQueryParams params)
	{
		return DAO.newInstance(P2p_plat.class).queryList(ParamUtils.buildDBQueryParams(params));
	}

	@Override
	public void savePlats(List<P2p_plat> plats)
	{
		DAO.newInstance(P2p_plat.class).save(plats);
	}

	@Override
	public List<P2p_record> queryRecords(RecordParams params)
	{
		return DAO.newInstance(P2p_record.class).queryList(ParamUtils.buildDBQueryParams(params));
	}

	@Override
	public void saveRecords(List<P2p_record> plats)
	{
		DAO.newInstance(P2p_record.class).save(plats);
	}
}
