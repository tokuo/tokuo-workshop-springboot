package jp.tokuo.sand.sec.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperty {

    private String demoMessage;
}
