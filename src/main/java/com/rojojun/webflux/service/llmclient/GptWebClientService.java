package com.rojojun.webflux.service.llmclient;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmChatResponseDto;
import com.rojojun.webflux.model.llmclient.LlmType;
import com.rojojun.webflux.model.llmclient.gpt.GptChatRequestDto;
import com.rojojun.webflux.model.llmclient.gpt.response.GptChatResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class GptWebClientService implements LlmWebClientService{

    private final WebClient webClient;

    @Value("${llm.gpt.key}")
    private String gptKey;

    public GptWebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto llmChatRequestDto) {
        GptChatRequestDto gptChatRequestDto = new GptChatRequestDto(llmChatRequestDto);
        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + gptKey)
                .bodyValue(gptChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new RuntimeException("API 요청 실패 : " + body));
                    });
                }))
                .bodyToMono(GptChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GPT;
    }
}
