package jp.tokuo.sand.controller;

import jp.tokuo.sand.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    public Map<String, String> demoController(){
        return demoService.returnRes("test");
    }
}
