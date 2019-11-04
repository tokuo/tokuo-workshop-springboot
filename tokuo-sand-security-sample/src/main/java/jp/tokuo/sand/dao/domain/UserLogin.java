package jp.tokuo.sand.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserLogin {

  @NotNull
  private long userId;

  @Email
  @Size(min = 1, max = 64)
  private String email;

  @Size(min=1, max=32)
  private String password;

  private boolean admin;
}
