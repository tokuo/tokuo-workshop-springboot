package jp.tokuo.workshop.controller;

import jp.tokuo.workshop.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    private final DemoService demoService;

    @Autowired
    public MainController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    public Map<String, String> demoController(){
        return demoService.returnRes("test");
    }
}
