package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BasicMonoOperatorTest {

    // just, empty
    @Test
    public void startMonoFromData() {
        Mono.just(1).subscribe(data -> System.out.println("data = " + data));

        Mono.empty().subscribe(data -> System.out.println("empty data = " + data));
    }

    // fromCallable, defer
    /**
     * fromCallable -> 동기적인 개체 반환
     * defer -> Mono 반환하고 싶을 때 사용
     * */
    @Test
    public void startMonoFromFunction() {
        Mono<String> monoFromCallable = Mono.fromCallable(() -> {
            return callRestTemplate("안녕");
        }).subscribeOn(Schedulers.boundedElastic());

        Mono<String> monoFromDefer = Mono.defer(() -> {
            return callWebClient("안녕!");
        });
        monoFromDefer.subscribe();
        Mono<String> monoFromJust = Mono.just("안녕!");
    }

    @Test
    public void testDeferNecessity() {
        Mono.defer(() -> {
            String a = "안녕";
            String b = "하세";
            String c = "요";

            return callWebClient(a + b + c);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<String> callWebClient(String request) {
        return Mono.just(request + "callWebClient");
    }

    public String callRestTemplate(String request) {
        return request = "callRestTemplate 응답";
    }
}
