package jp.tokuo.sand.reactive.controller;

import jp.tokuo.sand.reactive.service.ReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ReactiveController {

  private final WebClient webClient;
  private final ReactiveService reactiveService;

  public ReactiveController(ReactiveService reactiveService){
    this.webClient = WebClient.create("http://localhost:8080");
    this.reactiveService = reactiveService;
  }


  @GetMapping("/show/reactor/behavior")
  public ResponseEntity<String> showBehavior() {
    reactiveService.startReactiveStream();
    return ResponseEntity.ok("watch log");
  }

  @GetMapping("/test/webClient")
  public Mono<String> getWebClientTest(){
    return webClient.get()
      .uri("/api/v1/test/sleep")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
      .map(String::toUpperCase)
      .log();
//      .subscribe() をWebFluxでは記述しない
    }
}
