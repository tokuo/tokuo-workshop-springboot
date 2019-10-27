package jp.tokuo.sand.controller;

import jp.tokuo.sand.security.LoginUser;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(path = "/home")
  public String home(@AuthenticationPrincipal LoginUser loginUser) {
    if (loginUser != null) {
      return "redirect:/hoge";
    }
    return "home";
  }
}
