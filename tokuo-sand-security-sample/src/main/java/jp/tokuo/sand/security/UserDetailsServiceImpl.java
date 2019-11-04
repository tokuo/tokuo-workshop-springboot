package jp.tokuo.sand.security;

import jp.tokuo.sand.dao.UserDao;
import jp.tokuo.sand.dao.domain.UserLogin;

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
  //TODO データベースに格納しているパスワードが平文の時利用（平文を入れるのはDBの運用法としては適切ではない）
  private final PasswordEncoder passwordEncoder;

  public UserDetailsServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  // SecurityConfigに設定されているusername(ここではemail)が引数に渡される
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    // emailでユーザーを検索する
    UserLogin userLogin = userDao.getUserInfoByEmail(email);
    if (userLogin == null) {
      throw new UsernameNotFoundException("User not Found");
    }

    // 格納される情報を返す。
    return new LoginUser(
        userLogin.getUserId(),
        userLogin.getEmail(),
        passwordEncoder.encode(userLogin.getPassword()),
        userLogin.isAdmin(),
        this.determineRoles(userLogin.isAdmin()),
        //userInfo.getExpiredAt()
        LocalDateTime.of(2022,03,31, 0,0)
    );
  }

  private List<GrantedAuthority> determineRoles(boolean isAdmin) {
    return isAdmin ? UserRole.ADMIN.getGrantedAuthority() : UserRole.USER.getGrantedAuthority();
  }

}
