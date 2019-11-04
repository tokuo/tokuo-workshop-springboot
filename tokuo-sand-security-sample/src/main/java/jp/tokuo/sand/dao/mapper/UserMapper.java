package jp.tokuo.sand.dao.mapper;

import jp.tokuo.sand.dao.domain.UserLogin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT user_id, email, password, admin FROM login_user WHERE email = #{email}")
  UserLogin selectUserByEmail(@Param("email") String email);

  @Insert("INSERT INTO login_user(name, email, password) VALUES(#{name}, #{email}, #{password})")
  void insertUser(@Param("name") String name, @Param("email") String email, @Param("password") String password);
}
