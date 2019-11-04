package jp.tokuo.sand.controller;

import jp.tokuo.sand.dao.domain.UserSignup;
import jp.tokuo.sand.service.SignupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignupController {
  private static final Logger log = LoggerFactory.getLogger(SignupController.class);

  private final SignupService signupService;

  public SignupController(SignupService signupService){
    this.signupService = signupService;
  }


  @PostMapping("/signup")
  public String signupPost(Model model, @Valid UserSignup userSignup, BindingResult bindingResult, HttpServletRequest req) {
    if (bindingResult.hasErrors()) {
      return "redirect:/login";
    }

    //TODO error-handler
    signupService.registerUser(userSignup.getName(), userSignup.getEmail(), userSignup.getPassword());

    try {
      // 内部的にはAuthenticationManagerを使用して認証する
      // https://www.codeflow.site/ja/article/spring-security-auto-login-user-after-registration
      req.login(userSignup.getEmail(), userSignup.getPassword());
    } catch (ServletException e) {
      log.error("Error while login ", e);
    }

    return "redirect:/home";
  }
}
