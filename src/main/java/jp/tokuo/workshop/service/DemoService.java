package jp.tokuo.workshop.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DemoService {

    public Map<String, String> returnRes(String returnStr){
        Map<String, String> res = new HashMap<>();
        res.put("status", returnStr);
        return res;
    }
}
