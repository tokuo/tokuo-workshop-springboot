package jp.tokuo.sand.dao.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserSignup {

  @Email
  @Size(min = 1, max = 64)
  private String email;

  @Size(min = 1, max = 16)
  private String name;

  @Size(min=1, max=32)
  private String password;
}
