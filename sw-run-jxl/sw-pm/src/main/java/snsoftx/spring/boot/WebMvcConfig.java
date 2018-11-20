package snsoftx.spring.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明： WebMvcConfigurerAdapter在SroingBoot2.0已经废弃使用官方推荐方式重写WebMvcConfigurer
 * </pre>
 * <p>作者：张东</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018-11-15 16:01</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addRedirectViewController("/", "/start.html");
		registry.addRedirectViewController("/admin", "/admin/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
