package com.rojojun.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reactive")
public class ReactiveProgrammingExampleController {

    // 1~9 까지 출력하는 API
    @GetMapping("/onenine/legacy")
    public Mono<List<Integer>> produceOneToNineLegacy() {
        return Mono.fromCallable(() -> {
            List<Integer> sink = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                sink.add(i);
            }
            return sink;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/onenine/list")
    public List<Integer> produceOneToNine() {
        List<Integer> sink = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            sink.add(i);
        }
        return sink;
    }

    @GetMapping("/onenine/flux")
    public Flux<Integer> produceOneToNineFlux() {
      return Flux.<Integer>create(sink -> {
            for (int i = 1; i <= 9; i++) {
                try {
                    log.info("Thead : {}", Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                sink.next(i);
            }
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
        // 리액티브 스트림 구현체 Flux, Mono로 사용하여 발생되는 데이터를 바로바로 리액티브하게 처리
        // 비동기로 로직 - 논 블로킹하게 동작

        // 리액티브 프로그래밍 필수 요소
        // 1. 데이터가 준비될 때마다 바로바로 리액티브 처리 > 리액티브 스트림 구현체 Flux, Mono 사용
        // 2. 로직을 작성할 때는 반드시 논 블로킹하게 짜야함 > 이를 위해 비동기 프로그래밍 필요
    }
}
