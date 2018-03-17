package sw.study;

import java.util.Arrays;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import sw.study.service.UserService;
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
 * <p>创建日期：2018年3月16日下午2:19:26</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class InsertTest extends MyBatisTest
{
	@Test
	public void test()
	{
		int id = 100;
		UserService userService = getBean("userService");
		User user = new User();
		user.setId(id);
		userService.insertUsers(Arrays.asList(user));
		//
		AssertJUnit.assertNotNull(userService.query(id));
		//
		userService.delete(user);
		AssertJUnit.assertNull(userService.query(id));
	}
}
