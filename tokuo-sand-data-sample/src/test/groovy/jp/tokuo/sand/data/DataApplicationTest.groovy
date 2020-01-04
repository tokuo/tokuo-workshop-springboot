package jp.tokuo.sand.data


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DataApplicationTest extends Specification {

  def "test"() {
    expect: "ただ実行するだけ"
    1 == 1
  }
}
