package com.rojojun.webflux.service.user.chat;

import com.rojojun.webflux.model.user.chat.UserChatRequestDto;
import com.rojojun.webflux.model.user.chat.UserChatResponseDto;
import reactor.core.publisher.Mono;

public interface UserChatService {
    Mono<UserChatResponseDto> getOneShotChat(UserChatRequestDto userChatRequestDto);
}
