package sw.study.service;

import java.util.List;
import java.util.Set;
import sw.study.user.User;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月16日上午11:26:17</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface UserService
{
	void printUsers(int id);

	void insertUsers(List<User> users);

	User query(int id);

	void delete(User user);

	/**
	 * 多线程插入
	 * @param total
	 */
	void createRecords(int total);

	/**
	 * 多线程统计
	 * @return
	 */
	Info stat();

	class Info
	{
		private Set<Integer>	ids;
		private long			count;

		public Info(Set<Integer> ids, long count)
		{
			super();
			this.ids = ids;
			this.count = count;
		}

		public Set<Integer> getIds()
		{
			return ids;
		}

		public void setIds(Set<Integer> ids)
		{
			this.ids = ids;
		}

		public long getCount()
		{
			return count;
		}

		public void setCount(long count)
		{
			this.count = count;
		}
	}
}
