package jp.tokuo.sand.sec.service;

import jp.tokuo.sand.sec.dao.UserDao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SignupService {

  private final UserDao userDao;

  public SignupService(UserDao userDao){
    this.userDao = userDao;
  }


  @Transactional(rollbackFor = Throwable.class)
  public void registerUser(String email, String name, String password){
    userDao.registerUser(email, name, password);
  }
}
