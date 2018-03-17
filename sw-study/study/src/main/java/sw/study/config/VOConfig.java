package sw.study.config;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.Marshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * <br><b>标题：</b>
 * <br><b>功能： </b>
 * <pre>
 * </pre>
 * @author 冀小雷
 * @version 4.0.0
 * @since 2017年8月2日下午5:08:12
 */
@Configuration
public class VOConfig
{
	/**
	 * 获取Jaxb对象，用来进行xml与javabean的互相转换
	 * 多数用于SpringMVC中Json/Xml自动转换为实体对象
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller()
	{
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		Map<String,Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 转XML格式化
		properties.put(Marshaller.JAXB_FRAGMENT, false);
		jaxb2Marshaller.setMarshallerProperties(properties);
		jaxb2Marshaller.setLazyInit(true);
		String[] packagesToScan = {
				//
				"sw.*.order" };
		jaxb2Marshaller.setPackagesToScan(packagesToScan);
		return jaxb2Marshaller;
	}

	@Bean
	public ObjectMapper objectMapper()
	{
		return new ObjectMapper();
	}
}
