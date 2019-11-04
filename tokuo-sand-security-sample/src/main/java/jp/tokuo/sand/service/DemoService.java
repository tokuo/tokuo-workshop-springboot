package jp.tokuo.sand.service;

import jp.tokuo.sand.property.DemoProperty;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties(DemoProperty.class)
public class DemoService {

    private final DemoProperty property;

    public DemoService(DemoProperty property){
        this.property = property;
    }

    public Map<String, String> returnRes(String returnStr){
        Map<String, String> res = new HashMap<>();
        res.put("status", returnStr);
        res.put("message", property.getDemoMessage());
        return res;
    }
}
