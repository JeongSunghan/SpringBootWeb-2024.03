package com.example.sb.users;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {
	//User 어떻게 가져올거야? => String uid를 가져와야 돼 => @Select문 작성

	@Select("select * from users where uid=#{uid}")
	User getUser(String uid);
	
	@Select("select * from users where isDeleted=0 order by regDate desc"
			+ " limit #{count} offset #{offset}")
	List<User> getUserList(int count, int offset);
	
	@Insert("insert into users values (#{uid}, #{pwd}, #{uname}, #{email}, default, default)")
	void insertUser(User user);
	
	@Update("update users set pwd=#{pwd}, uname=#{uname}, email=#{email} where uid=#{uid}")
	void updateUser(User user);
	
	@Update("update users set isDeleted=1 where uid=#{uid}")
	void deleteUser(String uid);
	
	@Select("select count(uid) from users where isDeleted=0")
	int getUserCount();
}
