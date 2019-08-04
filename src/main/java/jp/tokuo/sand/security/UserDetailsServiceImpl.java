package jp.tokuo.sand.security;

import jp.tokuo.sand.dao.UserDao;
import jp.tokuo.sand.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserDao userDao;

  //TODO ベタがきのため利用、データベース構築後削除
  @Autowired
  PasswordEncoder passwordEncoder;

  public UserDetailsServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  // SecurityConfigに設定されているusername(ここではemail)が引数に渡される
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    // emailでユーザーを検索する
    User user = userDao.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not Found");
    }

    // 格納される情報を返す。
    return new LoginUser(
        //user.getUserId(),
        // user.getEmail(),
        //user.getPassword(),
        //user.getAdministratorFlag(),
        //user.getAdministratorFlag()),
        //user.getExpiredAt()
        (long)10,
        "user1",
        passwordEncoder.encode("pass1"),
        false,
        this.determineRoles(false),
        LocalDateTime.of(2022,03,31, 0,0)
    );
  }

  private List<GrantedAuthority> determineRoles(boolean isAdmin) {
    return isAdmin ? UserRole.ADMIN.getGrantedAuthority() : UserRole.USER.getGrantedAuthority();
  }

}
