package com.rojojun.webflux.model.llmclient.gpt;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmModel;
import com.rojojun.webflux.model.llmclient.gpt.response.GptResponseMessageDto;

import java.util.List;

public record GptChatRequestDto(
        LlmModel llmModel,
        Boolean stream,
        List<GptCompletionRequestDto> message
) {
    public GptChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
        this.message = List.of(GptResponseMessageDto)
    }
}
