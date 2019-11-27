package jp.tokuo.sand.sec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * spring securityのフロー
 * 1. リクエストが来たらSecurityFilter/AuthenticationFilterを通る
 * 2. AuthenticationManagerがAuthenticationProviderを呼び出す
 * 3. AuthenticationProviderがUserDetailsSerivceを呼び出す
 * 4. UserDetailsSerivceが、UserDetailsを返す
 * 5. 認証が成功すると、AuthenticationFilterが、AuthenticationSuccessHandlerを呼び出す
 * 5. 認証が失敗すると、AuthenticationFilterが、AuthenticationFailureHandlerを呼び出す
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public static final String LOGIN_URL = "/login";
  public static final String SIGNUP_URL = "/signup";
  public static final String ERROR_URL = "/error/**";
  public static final String TOP_PAGE_URL = "/home";

  //TODO spring actuatorとspring securityの設定

  // TODO inmemory for test
//  @Override
//  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER")
//        .and()
//        .withUser("user2").password(passwordEncoder().encode("pass2")).roles("USER")
//        .and()
//        .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//  }

  // database
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
    auth.eraseCredentials(true)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }


  @Override
  public void configure(WebSecurity web) {
    // 静的リソースに対するアクセスはセキュリティ設定を外す
    web.ignoring().antMatchers("/img/**", "/css/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // ログイン（認証）
    // フォーム認証ではUsernamePasswordAuthenticationFilterが設定される
    http.formLogin().loginPage(LOGIN_URL)
        // ManagerはデフォルトでProviderManager、ProviderはDaoAuthenticationProvider
        .loginProcessingUrl("/form/login") // ログインボタン押下時に指定するパスと合わせる
        .defaultSuccessUrl(TOP_PAGE_URL, true).usernameParameter("email").passwordParameter("password")
        .successHandler(new SuccessHandler(TOP_PAGE_URL)) // 認証成功時のコールハンドラ
        .failureHandler(new FailureHandler()).and(); // 認証失敗時のコールハンドラ

    // ログアウト
    http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウト処理のパス マッチャーで当てたいのでこうなっている？
        .logoutSuccessUrl(LOGIN_URL) // ログアウト後の遷移先
        .deleteCookies("JSESSIONID").and(); // 破棄対象のセッションID。Spring Sessionを利用した場合、どう関連するのか？

    // 認可
    http.authorizeRequests()
        .antMatchers(LOGIN_URL, SIGNUP_URL, ERROR_URL).permitAll() // 全許可
        .antMatchers(TOP_PAGE_URL).hasRole("USER") // アカウント保持者対象
        .antMatchers("/user/**").hasRole("ADMIN") // 管理権限対象
        .anyRequest().authenticated().and(); // 指定以外はアクセス不可 「/」はLOGIN_URLにリダイレクトされる

    // CSRFトークン生成
    http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
