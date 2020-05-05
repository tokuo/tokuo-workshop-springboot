package jp.tokuo.sand.reactive.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReactiveServiceTest {

  private final ReactiveService reactiveService;

  @Autowired
  public ReactiveServiceTest(ReactiveService reactiveService){
    this.reactiveService = reactiveService;
  }


  @DisplayName("効果的なserviceクラスのテストを模索中")
  @Test
  public void startReactiveStreamTest(){
    reactiveService.startReactiveStream();
  }
}
