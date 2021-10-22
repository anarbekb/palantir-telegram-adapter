package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;

@Component
@Slf4j
public class PalantirListener {
    @StreamListener(ChannelBinding.CHANNEL_USER_SEARCH_RS)
    public void receiveUser(SearchUserResponseDto response) {
        log.info("Received request for requestId {} user {}, find: {}, results: {}",
            response.getRequestId(), response.getQuery(), response.isFind(), response.getFinds());
    }
}