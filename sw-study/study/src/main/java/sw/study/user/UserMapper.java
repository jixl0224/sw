package sw.study.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年3月16日上午10:15:42</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface UserMapper
{
	@Select("SELECT * FROM test_users WHERE id = #{userId}")
	User getUser(@Param("userId") int userId);
}
