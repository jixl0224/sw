package snsoftx.spring.boot;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import snsoft.servlet.SessionTrace;
import snsoft.servlet.filter.DefaultFilter;
import snsoft.servlet.filter.UserSessionFilter;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月10日下午2:03:38</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class BootConfig
{
	@Autowired
	protected Environment env;

	@Bean
	public DefaultFilter filter_DefaultFilter()
	{
		DefaultFilter filter = new DefaultFilter();
		return filter;
	}

	@Bean
	public FilterRegistrationBean<DefaultFilter> fitlerRegister(DefaultFilter myFilter)
	{
		FilterRegistrationBean<DefaultFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(myFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("filters", "*.~gzip,*.$gzip:snsoft.servlet.filter.AddGZipHeaderFilter");
		return filterRegistrationBean;
	}

	@Bean
	public UserSessionFilter filter_userSession()
	{
		return new UserSessionFilter();
	}

	@Bean
	public FilterRegistrationBean<UserSessionFilter> filter_userSession_register(UserSessionFilter myFilter)
	{
		FilterRegistrationBean<UserSessionFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(myFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/ui/*");
		filterRegistrationBean.addUrlPatterns("/cxfservices/*");
		filterRegistrationBean.addUrlPatterns("/uiinvoke/*");
		filterRegistrationBean.addUrlPatterns("/fs/*");
		filterRegistrationBean.addUrlPatterns("/ws/*");
		filterRegistrationBean.addUrlPatterns("/do/*");
		filterRegistrationBean.addUrlPatterns("/office/wopi/files/*");
		return filterRegistrationBean;
	}

	@Bean
	public SessionTrace listener_SessionTrace()
	{
		return new SessionTrace();
	}

	@Bean
	public BootFactory bootFactory()
	{
		return new BootFactory();
	}
	//	/**
	//	 * 废弃
	//	 * @return
	//	 */
	//	@Bean
	//	public WebMvcConfigurerAdapter webMvcConfig()
	//	{
	//		return new WebMvcConfigurerAdapter()
	//		{
	//			@Override
	//			public void addViewControllers(ViewControllerRegistry registry)
	//			{
	//
	//			}
	//
	//			@Override
	//			public void addResourceHandlers(ResourceHandlerRegistry registry)
	//			{
	//			}
	//		};
	//	}
	public class BootFactory extends TomcatServletWebServerFactory
	{
		@Override
		protected void configureContext(Context context, ServletContextInitializer[] initializers)
		{
			super.configureContext(context, initializers);
			context.addPropertyChangeListener(new PropertyChangeListener()
			{
				@Override
				public void propertyChange(PropertyChangeEvent evt)
				{
					if ("resources".equals(evt.getPropertyName()))
					{
						StandardContext context = (StandardContext) evt.getSource();
						WebResourceRoot root = context.getResources();
						StandardRoot xx = (StandardRoot) root;
						for (int i = 1; ; i++)
						{
							String path = env.getProperty("web.path" + i);
							if (path == null)
							{
								break;
							}
							xx.addPreResources(new DirResourceSet(root, "/", path, "/"));
						}
					}
				}
			});
		}
	}
}
