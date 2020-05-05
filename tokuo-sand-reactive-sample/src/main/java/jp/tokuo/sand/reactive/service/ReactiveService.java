package jp.tokuo.sand.reactive.service;

import jp.tokuo.sand.reactive.publisher.DemoPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReactiveService {
  private final DemoPublisher demoPublisher;

  public ReactiveService(DemoPublisher demoPublisher){
    this.demoPublisher = demoPublisher;
  }


  public void startReactiveStream() {
    // start Reactive Stream
    demoPublisher.getName().get().subscribe(name -> {
      demoPublisher.getAttribute().subscribe(attribute -> {
        demoPublisher.getComment(name, attribute);
      });
    });
    // end Reactive Stream
    log.info("This logging message is not in the reactive stream.");
  }
}
