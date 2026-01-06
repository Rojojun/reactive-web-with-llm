package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    @Test
    public void testBasicFluxMono() {
        Mono.just(2)
                .map(data -> {
                    System.out.println("map thread name = " + Thread.currentThread().getName());
                    return data * 2;
                })
                .publishOn(Schedulers.parallel())
                .filter(data -> {
                    System.out.println("filter thread name = " + Thread.currentThread().getName());
                    return data % 4 == 0;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(data -> System.out.println("data = " + data));
    }
}
