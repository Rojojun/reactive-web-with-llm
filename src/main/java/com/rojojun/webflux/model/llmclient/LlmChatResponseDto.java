package com.rojojun.webflux.model.llmclient;

import com.rojojun.webflux.model.llmclient.gpt.response.GptChatResponseDto;

public record LlmChatResponseDto(
        String llmResponse
) {
    public LlmChatResponseDto(GptChatResponseDto responseDto) {
        this(responseDto.getSingleChoice().message().content());
    }
}
