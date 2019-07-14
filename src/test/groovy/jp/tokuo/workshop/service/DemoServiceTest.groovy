package jp.tokuo.workshop.service

import spock.lang.Specification
import spock.lang.Unroll


class DemoServiceTest extends Specification {

    @Unroll
    def "test"() {
        given:
//        REPLACE = Mock()
        def target = new DemoService()

        when:
        def result = target.returnRes(returnStr)

        then:
        assert result.get("status") == expect

        where:
        returnStr   | expect
        "test"      | "test"
    }
}