package com.rojojun.webflux.model.user.chat;

import com.rojojun.webflux.model.llmclient.LlmChatResponseDto;

public record UserChatResponseDto(
    String response
) {
    public UserChatResponseDto(LlmChatResponseDto responseDto) {
        this(responseDto.llmResponse());
    }
}
