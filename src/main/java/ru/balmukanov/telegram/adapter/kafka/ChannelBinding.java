package ru.balmukanov.telegram.adapter.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ChannelBinding {
    String CHANNEL_USER_SEARCH_RQ = "userSearchRequest";
    String CHANNEL_USER_SEARCH_RS = "userSearchResponse";

    @Output(CHANNEL_USER_SEARCH_RQ)
    MessageChannel searchUserRequest();

    @Input(CHANNEL_USER_SEARCH_RS)
    MessageChannel searchUserResponse();
}
