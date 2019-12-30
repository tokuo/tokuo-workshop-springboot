package jp.tokuo.sand.data.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.tokuo.sand.data.interceptor.RestTemplateInterceptor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import lombok.Data;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataControllerMTest {

  private ObjectMapper objectMapper;
  private final TestRestTemplate testRestTemplate;
  private final RestOperations restOperations;

  @LocalServerPort
  private Integer port;

  @Autowired //テストではアノテーション必須
  public DataControllerMTest(ObjectMapper objectMapper, TestRestTemplate testRestTemplate,
      final RestTemplateBuilder restTemplateBuilder, final RestTemplateInterceptor restTemplateInterceptor){
    this.objectMapper = objectMapper;
    this.testRestTemplate = testRestTemplate;
    this.restOperations = restTemplateBuilder.additionalInterceptors(restTemplateInterceptor).build();
  }


  @DisplayName("入力したAPIのエンドポイントがresponseとして出力できていること")
  @ParameterizedTest
  @ValueSource(strings = {"hoge", "fuga", "piyo"})
  void getTest_20X_01(String candidate) throws Exception {
    // given
    ResponseEntity<String> response;
    final String LOCAL_URI = "http://localhost:" + port.toString() + "/api/v1/get/" + candidate;

    // when
//    response = testRestTemplate.getForEntity(LOCAL_URI, String.class);
    response = restOperations.getForEntity(LOCAL_URI, String.class);

    // then
    System.out.println(response.getBody());
    System.out.println(response.getHeaders());
    System.out.println(response.getStatusCode());
    System.out.println(response.getStatusCodeValue());
    ResponseBody responseBody = objectMapper.readValue(response.getBody(), ResponseBody.class);
    Assertions.assertEquals(responseBody.getCandidate(), candidate);
  }

  // SampleDataと同じ
  // jacksonはinner classではstatic class
  @Data
  static class ResponseBody{
    private String candidate;
  }
}
