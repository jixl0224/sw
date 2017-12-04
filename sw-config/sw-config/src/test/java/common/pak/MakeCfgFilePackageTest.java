package common.pak;

import java.text.MessageFormat;
import java.util.List;
import org.junit.Test;
import snsoft.cfg.common.exception.InvalidPathException;
import snsoft.cfg.common.pak.MakeCfgFilePackage;
import snsoft.cfg.common.util.FileUtils;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：wanglipeng</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class MakeCfgFilePackageTest
{
	@Test
	public void makePakTest() throws InvalidPathException
	{
		String rootPaths = FileUtils.getRootPaths();
		List<String> cfgList = FileUtils.getCfgList();
		MakeCfgFilePackage m = new MakeCfgFilePackage();
		String makePak = m.makePak(rootPaths, cfgList, "snadk-config/snadk-config/src/main/resources/snsoft/cfg-ver.inf");
		System.err.println(MessageFormat.format("生成的配置包文件:{0}", makePak));
	}
}
