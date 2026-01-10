package com.rojojun.webflux.model.llmclient.gpt.response;

public record GptChoice(
        String finishReason,
        GptResponseMessageDto message
) {
}
