package com.rojojun.webflux.model.llmclient.gpt;

public record GptCompletionRequestDto(
        String content,
        String role,
) {
}
