package jp.tokuo.sand.controller;

import jp.tokuo.sand.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// RestControllerではThymeleafが反応しない
@Controller
public class HomeController {

    private final DemoService demoService;

    @Autowired
    public HomeController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping(path = "/home")
    public String demoController(Model model){
        return "home";
    }
}
