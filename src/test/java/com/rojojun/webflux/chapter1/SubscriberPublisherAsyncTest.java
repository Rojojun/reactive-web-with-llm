package com.rojojun.webflux.chapter1;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscriberPublisherAsyncTest {

    @Test
    public void produceOneToNieFlux() {
        Flux<Integer> integerFlux = Flux.<Integer>create(integerFluxSink -> {
            for (int i = 1; i <= 9; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                integerFluxSink.next(i);
            }

            integerFluxSink.complete();
        }).subscribeOn(Schedulers.boundedElastic());

        integerFlux.subscribe(data -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
            System.out.println("Value : " + data);
        });
        System.out.println("END");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }
}
