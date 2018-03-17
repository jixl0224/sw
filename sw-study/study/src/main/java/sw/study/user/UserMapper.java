package sw.study.user;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Insert({ "INSERT INTO test_users VALUES(#{id},#{var00},#{var01},#{var02},#{var03},#{var04},#{var05},#{var06},#{var07},#{var08},#{var09})" })
	void insert(User user);

	@Update({ "UPDATE test_users SET var00=#{var00} where id=#{id}" })
	void updateVar00(User user);

	@Delete({ "DELETE FROM test_users where id=#{id}" })
	void delete(User user);

	@Delete({ "DELETE FROM test_users where id=#{id}" })
	void deleteById(int id);

	@Select("SELECT * FROM test_users WHERE id = #{id}")
	User getUserById(@Param("id") int id);

	@Select("SELECT * FROM test_users WHERE id = #{id}")
	User getUser(User user);

	@Select("SELECT count(*) FROM test_users")
	int selectCount();

	@Select("SELECT * FROM test_users WHERE limit #{skip},#{limit}")
	List<User> queryPage(int skip, int limit);
}
