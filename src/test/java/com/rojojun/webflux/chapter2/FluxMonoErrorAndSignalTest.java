package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoErrorAndSignalTest {

    @Test
    public void testBasicSignal() {
        Flux.just(1, 2, 3, 4, 5)
                .doOnNext(data -> System.out.println("publishData = " + data))
                .doOnComplete(() -> System.out.println("Stream is finished"))
                .doOnError(error -> System.out.println("error = " + error))
                .subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testFluxMonoError() {
        try {
            Flux.just(1, 2, 3, 4)
                    .map(data -> {
                        if (data == 3) {
                            throw new RuntimeException();
                        }

                        return data * 2;
                    })
                    .subscribe(data -> System.out.println("data = " + data));
        } catch (Exception e) {
            System.out.println("에러 발생");
        }
    }

    @Test
    public void testFluxMonoDotError() {
        Flux.just(1, 2, 3, 4)
                .flatMap(data -> {
                    if (data != 3) {
                        return Mono.just(data);
                    } else {
                        return Mono.error(new RuntimeException());
                    }
                }).subscribe(data -> System.out.println("data = " + data));
    }
}
