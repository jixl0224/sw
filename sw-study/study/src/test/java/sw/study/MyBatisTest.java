package sw.study;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月16日下午2:17:30</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public abstract class MyBatisTest
{
	protected ClassPathXmlApplicationContext context;

	@BeforeClass
	public void initContext()
	{
		String[] locations = new String[] {
				// 
				"beans.xml"
				//
		};
		context = new ClassPathXmlApplicationContext(locations);
		context.refresh();
		context.start();
	}

	protected <T> T getBean(String name)
	{
		return (T) context.getBean(name);
	}
}
