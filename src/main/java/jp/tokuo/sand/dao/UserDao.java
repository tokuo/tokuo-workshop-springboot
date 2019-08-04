package jp.tokuo.sand.dao;

import jp.tokuo.sand.domain.User;

public interface UserDao {

  User findByEmail(String email);

  User findById(Long userId);

  void add(User user);

  void set(User user);

  void remove(Long userId);
}
