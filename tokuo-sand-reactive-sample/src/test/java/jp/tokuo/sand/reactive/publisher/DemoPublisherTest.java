package jp.tokuo.sand.reactive.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

/**
 * reactor用のテストクラスは以下３つ
 * StepVerifier
 * TestPublisher
 * PublisherProbe
 */
@SpringBootTest
public class DemoPublisherTest {

  private final DemoPublisher demoPublisher;

  @Autowired
  public DemoPublisherTest(DemoPublisher demoPublisher){
    this.demoPublisher = demoPublisher;
  }


  @Test
  public void getNameTest(){
    final String[] subscriptionNames = {"Tokuo", "Korosuke", "John"};

    StepVerifier.withVirtualTime(demoPublisher.getName())
      .expectNext(subscriptionNames[0])
      .expectNext(subscriptionNames[1])
      .expectNext(subscriptionNames[2])
      .verifyComplete();
  }

  @Test
  public void getAttributeTest(){
    final String[] subscriptionAttributes = {"hoge", "fuga", "piyo"};

    StepVerifier.create(demoPublisher.getAttribute())
      .expectNext(subscriptionAttributes[0])
      .expectNext(subscriptionAttributes[1])
      .expectNext(subscriptionAttributes[2])
      .verifyComplete();
  }
}
