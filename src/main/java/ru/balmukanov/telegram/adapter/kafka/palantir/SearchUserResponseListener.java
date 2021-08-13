package ru.balmukanov.telegram.adapter.kafka.palantir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;

@Component
public class SearchUserResponseListener {
    private static final Logger logger = LoggerFactory.getLogger(SearchUserResponseListener.class);

    @StreamListener(ChannelBinding.CHANNEL_USER_SEARCH_RS)
    public void receiveUser(SearchUserResponseDto response) {
        logger.info("Received request for user {}, find: {}, results: {}", response.getQuery(), response.isFind(), response.getFinds());
    }
}