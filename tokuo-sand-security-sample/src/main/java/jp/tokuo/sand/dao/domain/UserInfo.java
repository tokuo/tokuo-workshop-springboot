package jp.tokuo.sand.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ユーザを表すドメインオブジェクト
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserInfo {

  /**
   * ユーザID
   */
  @NotNull
  private Long userId;

  /**
   * メールアドレス
   */
  @Email
  @Size(min = 1, max = 255)
  private String email;

  /**
   * パスワード
   */
  private String password;

  /**
   * パスワード有効期限
   */
  private LocalDateTime expiredAt;

  /**
   * 管理者フラグ
   */
  @NotNull()
  private Boolean administratorFlag;

  /**
   * 更新ユーザID
   */
  private Long updateUserId;

  /**
   * 更新日時
   */
  private LocalDateTime updatedAt;

  /**
   * バージョン番号
   */
  @NotNull()
  private Long lockVersion;

  /**
   * 削除フラグ
   */
  private Boolean deleteFlag;
}
