package jp.tokuo.sand.api


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApiApplicationTest extends Specification {

  def "test"() {
    expect: "ただ実行するだけ"
    1 == 1
  }
}
