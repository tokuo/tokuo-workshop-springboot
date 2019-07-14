package jp.tokuo.workshop.service

import org.junit.Rule
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.rule.OutputCapture
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@RunWith(Enclosed)
class DemoServiceMTest {

    @SpringBootTest //@AutoConfigureWebMvc when springMvc
    @EnableAutoConfiguration
    @ActiveProfiles("test")
//    @ImportAutoConfiguration(classes = [])
//    @ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
    @DirtiesContext
    static class MainTest extends Specification {

        @Autowired
        DemoService demoService

        @Rule
        OutputCapture outputCapture = new OutputCapture()

        def "mainTest"() {
            expect:
            def result = demoService.returnRes("ok")
            assert result.get("message") == "test"
        }
    }
}