package jp.tokuo.sand.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import jp.tokuo.sand.api.interceptor.RestTemplateInterceptor
import jp.tokuo.sand.api.model.SampleData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.client.RestOperations
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataControllerMTest extends Specification {

  @Autowired ObjectMapper objectMapper
  @Autowired TestRestTemplate testRestTemplate
  @Autowired RestTemplateBuilder restTemplateBuilder
  @Autowired RestTemplateInterceptor restTemplateInterceptor
  private RestOperations restOperations

  @LocalServerPort
  private Integer port;

  // spockではコンストラクタが利用できない
  def setup () {
    this.restOperations = restTemplateBuilder.additionalInterceptors(restTemplateInterceptor).build()
  }

  def "入力したAPIのエンドポイントがresponseとして出力できていること"(){
    given: "エンドポイントの設定"
    def LOCAL_URI = "http://localhost:" + port.toString() + "/api/v1/get/" + candidate

    when: "リクエストを送り、レスポンスを受け取る"
    def response = restOperations.getForEntity(LOCAL_URI, String)

    then: "レスポンスのチェック"
    def responseBody = objectMapper.readValue(response.body, SampleData)
    responseBody.candidate == candidate

    where:
    candidate << ["hoge", "fuga", "piyo"]
  }
}
