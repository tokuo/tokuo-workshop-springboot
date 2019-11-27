package jp.tokuo.sand.sec.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessHandler implements AuthenticationSuccessHandler {

  private final String TOP_PAGE_URL;

  public SuccessHandler(String topUrl){
    this.TOP_PAGE_URL = topUrl;
  }


  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
    //TODO 認証期限の確認等を記述
    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + TOP_PAGE_URL);
  }
}
