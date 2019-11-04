package jp.tokuo.sand.dao;

import jp.tokuo.sand.dao.domain.UserInfo;
import jp.tokuo.sand.dao.domain.UserLogin;

public interface UserDao {

  UserLogin getUserInfoByEmail(String email);

  void registerUser(String name, String email, String password);

  void add(UserInfo userInfo);

  void set(UserInfo userInfo);

  void remove(Long userId);
}
