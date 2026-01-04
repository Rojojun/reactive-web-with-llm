package com.rojojun.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@SpringBootTest
public class WebClientTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    public void testWebClient() {
        Flux<Integer> integerFlux = webClient.get()
                .uri("http://localhost:8080/reactive/onenine/flux")
                .retrieve()
                .bodyToFlux(Integer.class);

        integerFlux.subscribe(data -> {
            System.out.println(LocalDateTime.now() + "Thread : " + Thread.currentThread().getName());
            System.out.println("Value : " + data);
        });
        System.out.println("END");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }
}
