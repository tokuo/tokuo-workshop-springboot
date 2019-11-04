//package jp.tokuo.sand.service
//
//import org.junit.Rule
//import org.junit.experimental.runners.Enclosed
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.rule.OutputCapture
//import org.springframework.test.annotation.DirtiesContext
//import org.springframework.test.context.ActiveProfiles
//import spock.lang.Specification
//
//@RunWith(Enclosed)
//class DemoServiceMTest {
//
//    @SpringBootTest //@AutoConfigureWebMvc when springMvc
//    @EnableAutoConfiguration
//    //@SpringBootConfiguration // spring公式提供物のconfigurationを呼び出すためのEnableAutoConfiguration
//    //@ImportAutoConfiguration(classes = [])
//    //@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
//    @ActiveProfiles("test")
//    @DirtiesContext // DIコンテナのデータをテスト単位毎にクリア
//    static class MainTest extends Specification {
//
//        @Autowired
//        DemoService demoService
//
//        @Rule
//        OutputCapture outputCapture = new OutputCapture()
//
//        def "mainTest"() {
//            expect:
//            def result = demoService.returnRes("ok")
//            assert result.get("message") == "test"
//        }
//    }
//}