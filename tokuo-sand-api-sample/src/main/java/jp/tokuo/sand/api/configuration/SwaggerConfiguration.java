package jp.tokuo.sand.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// check localhost:8080/swagger-ui.html
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

  @Bean
  public Docket swaggerSpringMvcApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .protocols(Collections.singleton("http"))
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/api.*"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("tokuo-sand API")
        .description("description")
        .version("0.1.0")
        .contact(new Contact(
            "tokuo-sand-sprnigboot",
            "https://github.com/tokuo/tokuo-sand-springboot",
            "hogehoge@yahoo.co.jp")
        )
        .build();
  }
}
