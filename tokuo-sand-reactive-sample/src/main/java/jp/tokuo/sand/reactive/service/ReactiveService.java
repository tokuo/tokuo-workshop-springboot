package jp.tokuo.sand.reactive.service;

import jp.tokuo.sand.reactive.subscriber.DemoSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReactiveService {
  private final DemoSubscriber demoSubscriber;

  public ReactiveService(DemoSubscriber demoSubscriber){
    this.demoSubscriber = demoSubscriber;
  }


  public void startReactiveStream(){
    // start Reactive Stream
    demoSubscriber.getName().subscribe(name -> {
      demoSubscriber.getAttribute().subscribe(attribute ->
        log.info(String.format("=== %s: %s ===", name.toString() ,attribute.toString())));
      });
    // end Reactive Stream
    log.info("This logging message is not in the reactive stream.");
    }
}
