package snsoft.boot.test;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import snsoft.boot.test.service.BootTestService01;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：张东</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月19日下午5:32:34</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootTest(classes = { BootConfiguration.class })
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class UIBootTest extends AbstractTestNGSpringContextTests /*SnsoftTest*/
{
	//	@Mock
	//	private BootTestService01	bootTestService01;
	@MockBean
	private BootTestService01	bootTestService01;
	protected final String		say	= "Hello!";

	@BeforeMethod
	public void initMock()
	{
		//		MockitoAnnotations.initMocks(this);
		Mockito.when(bootTestService01.hello(say)).thenReturn(say);
	}

	@Test
	public void testNull()
	{
		AssertJUnit.assertNotNull(bootTestService01);
	}

	@Test
	public void testSayMethod()
	{
		AssertJUnit.assertEquals(say, bootTestService01.hello(say));
	}
}
@SpringBootApplication(scanBasePackageClasses = BootTestService01.class)
interface BootConfiguration
{
}
