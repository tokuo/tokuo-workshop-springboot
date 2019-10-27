package jp.tokuo.sand.dao;

import jp.tokuo.sand.domain.User;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

  @Override
  public User findByEmail(String email) {
    return new User();
  }

  @Override
  public User findById(Long userId) {
    return new User();
  }

  @Override
  public void add(User user) {
    //
  }

  @Override
  public void set(User user) {
    //
  }

  @Override
  public void remove(Long userId) {
    //
  }
}
