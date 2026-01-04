package com.rojojun.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicFluxOperatorTest {
    /**
     * Flux
     * 데이터 : just, empty, from~시리즈
     * 함수 : defer
     */
    @Test
    public void testFluxFromData() {
        Flux.just(1, 2, 3, 4)
                .subscribe(data -> System.out.println("data = " + data));

        List<Integer> basicList = List.of(1, 2, 3, 4);
        Flux.fromIterable(basicList)
                .subscribe(data -> System.out.println("data = " + data));
    }

    /**
     * Flux defer -> 안에서 Flux객체를 반환
     */
    @Test
    public void testFluxFromFunction() {
        Flux.defer(() -> {
            return Flux.just(1, 2, 3, 4);
        }).subscribe(data -> System.out.println("data from defer = " + data));

        Flux.create(sink -> {
            sink.next(1);
            sink.next(2);
            sink.next(3);
            sink.complete();
        }).subscribe(data -> System.out.println("data from sink = " + data));
    }

    @Test
    public void testSinkDetail() {
        Flux.<String>create(sink -> {
            AtomicInteger counter = new AtomicInteger(0);
            recursiveFunction(sink, counter);
        }).subscribe(data -> System.out.println("data from recursive = " + data));
    }

    public void recursiveFunction(FluxSink<String> sink, AtomicInteger counter) {
        if (counter.incrementAndGet() < 10) {
            sink.next("sink count " + counter);
            recursiveFunction(sink, counter);
        } else {
            sink.complete();
        }
    }

    @Test
    public void testFluxCollectList() {
        Mono<List<Integer>> listMono = Flux.<Integer>just(1, 2, 3, 4, 5)
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .collectList();

        listMono.subscribe(data -> System.out.println("data = " + data));
    }

    /**
     * Mono -> Flux 변환 : flatMapMono
     * Flux -> Mono 변환 : collectionList
     */
}
