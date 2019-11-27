package jp.tokuo.sand.sec.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Data;

@Data
public class LoginUser extends User {

  private Long userId;

  // 管理者フラグ
  private boolean administratorFlag;

  // 認証有効期限
  private LocalDateTime expiredAt;

  // username = email
  public LoginUser(Long userId, String username, String password, boolean administratorFlag,
      Collection<? extends GrantedAuthority> authorities, LocalDateTime expiredAt){
    super(username, password, authorities);
    this.userId = userId;
    this.administratorFlag = administratorFlag;
    this.expiredAt = expiredAt;
  }

}
