package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorFlatmapTest {
    /**
     * Mono<Mono<T>> -> Mono<T>
     */

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        Mono<Flux<Integer>> integerFlux = one.map(data -> {
            return Flux.just(data, data + 1, data + 2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }

//    @Test
//    public void testWebClientFlatMap() {
//        Flux.just(callWebClient())
//    }

    public Mono<String> callWebClient(String request, long delay) {
        return Mono.defer(() -> {
            try {
                Thread.sleep(delay);
                return Mono.just(request + " -> 딜레이 : " + delay);
            } catch (InterruptedException e) {
                return Mono.empty();
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
