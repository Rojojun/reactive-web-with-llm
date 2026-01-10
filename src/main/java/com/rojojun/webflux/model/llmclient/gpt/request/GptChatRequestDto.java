package com.rojojun.webflux.model.llmclient.gpt.request;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmModel;
import com.rojojun.webflux.model.llmclient.gpt.GptCompletionRequestDto;
import com.rojojun.webflux.model.llmclient.gpt.GptMessageRole;

import java.util.List;
import java.util.Optional;

public record GptChatRequestDto(
    List<GptCompletionRequestDto> messages,
    LlmModel llmModel,
    Boolean stream,
    GptResponseFormat response_format
) {
    public GptChatRequestDto(LlmChatRequestDto chatRequestDto) {
        if (chatRequestDto.useJson()) {
            response_format = new GptResponseFormat("json_object");
        }

        this.messages = List.of(new GptCompletionRequestDto(GptMessageRole.SYSTEM, chatRequestDto.systemPrompt(), new GptCompletionRequestDto(GptMessageRole.USER, chatRequestDto.userRequest())));
        this.llmModel = chatRequestDto.llmModel();
    }
}
