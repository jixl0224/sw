package snsoftboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import snsoft.commons.spring.SpringXmlApplicationContext;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月10日下午1:32:40</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootApplication(
		//
		scanBasePackages = { "snsoftboot" })
public class BootLaunch
{
	public static void main(String[] args)
	{
		SpringXmlApplicationContext resourceLoader = new SpringXmlApplicationContext();
		new SpringApplication(resourceLoader, BootLaunch.class).run(args);
	}
}
