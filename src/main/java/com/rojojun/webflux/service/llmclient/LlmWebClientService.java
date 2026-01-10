package com.rojojun.webflux.service.llmclient;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmChatResponseDto;
import com.rojojun.webflux.model.llmclient.LlmType;
import reactor.core.publisher.Mono;

public interface LlmWebClientService {
    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto llmChatRequestDto);

    LlmType getLlmType();
}
