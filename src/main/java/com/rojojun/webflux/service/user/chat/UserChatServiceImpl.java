package com.rojojun.webflux.service.user.chat;

import com.rojojun.webflux.model.llmclient.LlmChatRequestDto;
import com.rojojun.webflux.model.llmclient.LlmChatResponseDto;
import com.rojojun.webflux.model.llmclient.LlmType;
import com.rojojun.webflux.model.user.chat.UserChatRequestDto;
import com.rojojun.webflux.model.user.chat.UserChatResponseDto;
import com.rojojun.webflux.service.llmclient.LlmWebClientService;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserChatServiceImpl implements UserChatService {

    private final Map<LlmType, LlmWebClientService> llmWebClientServiceMap;

    public UserChatServiceImpl(Map<LlmType, LlmWebClientService> llmWebClientServiceMap) {
        this.llmWebClientServiceMap = llmWebClientServiceMap;
    }

    @Override
    public Mono<UserChatResponseDto> getOneShotChat(UserChatRequestDto userChatRequestDto) {
        LlmChatRequestDto llmChatRequestDto = new LlmChatRequestDto(userChatRequestDto, "요청에 적절히 응답해 주세요");
        Mono<LlmChatResponseDto> chatResponseDtoMono = llmWebClientServiceMap.get(userChatRequestDto.llmModel()).getChatCompletion(llmChatRequestDto);
        return chatResponseDtoMono.map(UserChatResponseDto::new);
    }
}
