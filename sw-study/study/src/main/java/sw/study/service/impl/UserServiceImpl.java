package sw.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.study.service.UserService;
import sw.study.user.User;
import sw.study.user.UserMapper;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月16日上午11:27:21</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;

	@Override
	public void printUsers(int id)
	{
		User user = userMapper.getUser(id);
		System.out.println(user);
	}

	@Override
	public void initUsers()
	{

	}
}
