package jp.tokuo.sand.reactive.publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Supplier;

@Component
@Slf4j
public class DemoPublisher {

    // わざわざSupplierを利用しなくてもよい
    public Supplier<Flux<String>> getName() {
        final String[] subscriptionNames = {"Tokuo", "Korosuke", "John"};

        return () -> Flux.just(subscriptionNames)
                .interval(Duration.ZERO)
                .map(i -> subscriptionNames[i.intValue()])
                .take(subscriptionNames.length)
                .log();
        // 以下の記述で良い（個人的に気持ち悪い）
//        return () -> Flux
//                .interval(Duration.ZERO)
//                .map(n -> subscriptionNames[n.intValue()])
//                .take(subscriptionNames.length)
//                .log();
    }

    // Supplierを利用しない場合はこのメソッド
    public Flux<String> getAttribute() {
        final String[] subscriptionAttributes = {"hoge", "fuga", "piyo"};

        return Flux.just(subscriptionAttributes)
                .interval(Duration.ZERO)
                .map(i -> subscriptionAttributes[i.intValue()])
                .take(subscriptionAttributes.length)
                .log();
        // 以下の記述で良い（個人的に気持ち悪い）
//        return Flux
//                .interval(Duration.ZERO)
//                .map(a -> subscriptionAttributes[a.intValue()])
//                .take(subscriptionAttributes.length)
//                .log();
    }

    public void getComment(String name, String attribute){
        final String message = String.format("name:%s, attribute:%s", name, attribute);

        Mono.just(message)
          .log()
          .subscribe(log::info);
    }
}
