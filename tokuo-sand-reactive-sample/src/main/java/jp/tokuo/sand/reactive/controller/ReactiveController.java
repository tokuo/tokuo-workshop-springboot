package jp.tokuo.sand.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    private final WebClient webClient;

    public ReactiveController(){
        this.webClient = WebClient.create("http://localhost:8080");
    }

    @GetMapping("nonblocking")
    public Mono<String> getMonoTest(){
        return webClient.get()
                .uri("/api/v1/get/hoge")
                .exchange()
                .log()
                .flatMap(res -> res.bodyToMono(String.class));
    }
}
