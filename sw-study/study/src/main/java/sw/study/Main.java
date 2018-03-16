package sw.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sw.study.service.UserService;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月16日上午9:54:15</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Main
{
	public static void main(String[] args)
	{
		String[] locations = new String[] {
				// 
				"beans.xml"
				//
		};
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);)
		{
			context.refresh();
			context.start();
			//
			UserService userService = (UserService) context.getBean("userService");
			userService.printUsers(1);
		}
	}
}
