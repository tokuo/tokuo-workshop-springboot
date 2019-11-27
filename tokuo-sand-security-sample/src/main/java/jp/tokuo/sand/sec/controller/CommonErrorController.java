package jp.tokuo.sand.sec.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CommonErrorController implements ErrorController {

  @RequestMapping(path = "/error")
  public String handleError(HttpServletResponse res) {
    if(res.getStatus() == HttpStatus.NOT_FOUND.value()) {
      return "404";
    }
    else if(res.getStatus() == HttpStatus.FORBIDDEN.value()) {
      return "403";
    }
    else if(res.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      return "500";
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
