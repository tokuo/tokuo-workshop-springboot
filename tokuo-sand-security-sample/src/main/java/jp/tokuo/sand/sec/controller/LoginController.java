package jp.tokuo.sand.sec.controller;

import jp.tokuo.sand.sec.security.LoginUser;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping(path = "/login")
  public String home(@AuthenticationPrincipal LoginUser loginUser) {
    if (loginUser != null) {
      return "redirect:/login";
    }
    return "login_form";
  }
}
