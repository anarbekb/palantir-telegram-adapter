package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;
import ru.balmukanov.telegram.application.api.SearchUserRequestService;

@Component
@RequiredArgsConstructor
@Slf4j
public class PalantirListener {
    private final SearchUserRequestService searchUserRequestService;
    @StreamListener(ChannelBinding.CHANNEL_USER_SEARCH_RS)
    public void receiveUser(SearchUserResponseDto response) {
        log.info("Received request for requestId {} user {}, find: {}, results: {}",
            response.getRequestId(), response.getQuery(), response.isFind(), response.getFinds());

        searchUserRequestService.completeUserSearch(response.getRequestId());
    }
}