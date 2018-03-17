package sw.study.service.impl;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
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
		User user = userMapper.getUserById(id);
		System.out.println(user);
	}

	@Override
	public void insertUsers(List<User> users)
	{
		for (User user : users)
		{
			userMapper.insert(user);
		}
	}

	@Override
	public User query(int id)
	{
		return userMapper.getUserById(id);
	}

	@Override
	public void delete(User user)
	{
		userMapper.delete(user);
	}

	@Override
	public void createRecords(int total)
	{
		SyncResource resource = new SyncResource();
		Runnable task = () -> {
			int id = resource.nextId();
			//while ((id = resource.nextId()) < total)
			{
				try
				{
					System.out.println(id);
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
				}
			}
		};
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(4);
//		executor.submit(task);
//		executor.submit(task);
//		executor.submit(task);
//		executor.submit(task);
//		executor.submit(task);
		for(int i = 0;i < total;i++)
		{
			executor.submit(task);
		}
		executor.shutdown();
	}

	static class SyncResource
	{
		final AtomicInteger id = new AtomicInteger(1);

		public SyncResource()
		{
			super();
		}

		int nextId()
		{
			return id.getAndIncrement();
		}
	}

	@Override
	public Info stat()
	{
		return null;
	}
}
