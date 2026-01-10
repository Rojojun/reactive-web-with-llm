package com.rojojun.webflux.service.llmclient;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmChatResponseDto;
import com.rojojun.webflux.model.llmclient.LlmType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class GeminiWebClientService implements LlmWebClientService {
    private final WebClient webClient;

    @Value("${llm.gemini-key}")
    private String geminiKey;

    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto llmChatRequestDto) {
        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/model/gemini-3.0-flash:generateContent?key=" + geminiKey);
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GEMINI;
    }
}
