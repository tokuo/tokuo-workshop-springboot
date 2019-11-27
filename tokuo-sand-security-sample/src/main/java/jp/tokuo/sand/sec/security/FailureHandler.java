package jp.tokuo.sand.sec.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO リファクタ
public class FailureHandler extends ExceptionMappingAuthenticationFailureHandler {

  private static final Logger logger = LoggerFactory.getLogger(FailureHandler.class);

  public FailureHandler() {
    this.setDefaultFailureUrl("/");
    this.setExceptionMappings(getFailureUrlMap());
  }

  private Map<String, String> getFailureUrlMap() {
    Map<String, String> map = new HashMap<>();
    map.put(InternalAuthenticationServiceException.class.getName(), "/error/auth");
    return map;
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    logger.error("Error : " + exception);
    super.onAuthenticationFailure(request, response, exception);
  }
}
