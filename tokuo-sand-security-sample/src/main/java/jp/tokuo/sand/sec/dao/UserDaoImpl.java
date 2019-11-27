package jp.tokuo.sand.sec.dao;

import jp.tokuo.sand.sec.dao.domain.UserInfo;
import jp.tokuo.sand.sec.dao.domain.UserLogin;
import jp.tokuo.sand.sec.dao.mapper.UserMapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

  private final SqlSession sqlSession;

  public UserDaoImpl(SqlSession sqlSession){
    this.sqlSession = sqlSession;
  }


  @Override
  public UserLogin getUserInfoByEmail(String email) {
    return sqlSession.getMapper(UserMapper.class).selectUserByEmail(email);
  }

  @Override
  public void registerUser(String name, String email, String password){
    sqlSession.getMapper(UserMapper.class).insertUser(name, email, password);
  }

  @Override
  public void add(UserInfo userInfo) {
    //
  }

  @Override
  public void set(UserInfo userInfo) {
    //
  }

  @Override
  public void remove(Long userId) {
    //
  }
}
