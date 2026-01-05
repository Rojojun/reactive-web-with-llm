package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxMonoErrorAndSignalTest {

    @Test
    public void testBasicSignal() {
        Flux.just(1, 2, 3, 4, 5)
                .doOnNext(data -> System.out.println("publishData = " + data))
                .doOnComplete(() -> System.out.println("Stream is finished"))
                .doOnError(error -> System.out.println("error = " + error))
                .subscribe(data -> System.out.println("data = " + data));
    }
}
