package com.rojojun.webflux.model.user.chat;

import com.rojojun.webflux.model.llmclient.LlmModel;

public record UserChatRequestDto(
        String request,
        LlmModel llmModel
) {
}
