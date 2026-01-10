package com.rojojun.webflux.model.llmclient;

import com.rojojun.webflux.model.user.chat.UserChatRequestDto;

public record LlmChatRequestDto(
        String userRequest,
        String systemPrompt,
        boolean useJson,
        LlmModel llmModel
) {
    public LlmChatRequestDto(UserChatRequestDto userChatRequestDto, String systemPrompt) {
        this.llmModel = userChatRequestDto.llmModel();
        this.systemPrompt = systemPrompt;
        this.userRequest = userChatRequestDto.request();
    }
}
