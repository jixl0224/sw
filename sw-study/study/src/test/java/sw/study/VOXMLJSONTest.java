package sw.study;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sw.study.order.Bill;
import sw.study.order.Good;

/**
 * <p>标题：报文转换测试类</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月17日上午10:47:17</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class VOXMLJSONTest extends MyBatisTest
{
	private Bill bill;

	@BeforeClass
	public void initTest()
	{
		bill = new Bill();
		bill.setBillid(1);
		bill.setFcode("CNY");
		bill.setOdate(new Date());
		bill.setUser("Zhangsan");
		bill.setFcy(new BigDecimal("100"));
		bill.setFee(new BigDecimal("2"));
		// 
		Good good = new Good();
		good.setGoodid(1);
		good.setGcode("苹果");
		good.setUnit("公斤");
		good.setUpric(new BigDecimal("5"));
		good.setQtc(new BigDecimal("15"));
		good.setFcy(new BigDecimal("75"));
		bill.getGoods().add(good);
		//
		good = new Good();
		good.setGoodid(2);
		good.setGcode("金条");
		good.setUnit("克");
		good.setUpric(new BigDecimal("100"));
		good.setQtc(new BigDecimal("0.25"));
		good.setFcy(new BigDecimal("25"));
		bill.getGoods().add(good);
	}

	@Test
	public void testXML()
	{
		Jaxb2Marshaller marshaller = getBean("jaxb2Marshaller");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(bos);
		marshaller.marshal(bill, result);
		String text = new String(bos.toByteArray());
		System.out.println("序列化xml值：" + text);
		//
		Object v = marshaller.unmarshal(new StreamSource(new ByteArrayInputStream(bos.toByteArray())));
		System.out.println(v);
	}
	
	@Test
	public void testJSON()
	{
		
	}
}
