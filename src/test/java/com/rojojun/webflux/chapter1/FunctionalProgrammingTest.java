package com.rojojun.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

@SpringBootTest
public class FunctionalProgrammingTest {

    @Test
    public void produceOneToNine() {
        IntStream.rangeClosed(1, 9)
                .map(value -> value * 2)
                .filter(value -> value % 4 == 0)
                .forEach(System.out::println);
    }

    @Test
    public void produceOneToNineFlux() {
        Flux.fromIterable(IntStream.rangeClosed(1, 9).boxed().toList())
                .map(data -> data * 4)
                .filter(data -> data % 4 == 0)
                .subscribe(System.out::println);
    }
}
