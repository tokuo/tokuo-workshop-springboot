package jp.tokuo.sand.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityErrorController {

  @GetMapping(path = "/error/auth")
  public String auth(Model model) {
    model.addAttribute("error", "Internal Server Error");
    return "error_auth";
  }
}
