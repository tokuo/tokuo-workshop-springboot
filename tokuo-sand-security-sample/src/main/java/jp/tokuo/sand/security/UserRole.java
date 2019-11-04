package jp.tokuo.sand.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

public enum UserRole {

  // 管理者
  ADMIN() {
    @Override
    public List<GrantedAuthority> getGrantedAuthority() {
      return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    }
  },

  // 一般ユーザ
  USER() {
    @Override
    public List<GrantedAuthority> getGrantedAuthority() {
      return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
  };

  public abstract List<GrantedAuthority> getGrantedAuthority();
}
