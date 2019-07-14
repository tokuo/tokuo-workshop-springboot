package jp.tokuo.workshop.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperty {

    private String demoMessage;
}
