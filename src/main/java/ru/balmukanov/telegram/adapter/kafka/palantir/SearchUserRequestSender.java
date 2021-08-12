package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;

@Service
@RequiredArgsConstructor
public class SearchUserRequestSender {
    private static final Logger logger = LoggerFactory.getLogger(SearchUserRequestSender.class);
    private final ChannelBinding channelBinding;

    public void send(String userName) {
        var request = new SearchUserRequestDto(userName);

        boolean published = channelBinding.searchUserRequest().send(MessageBuilder.withPayload(request).build());
        if (!published) {
            throw new MessageDeliveryException(
                    String.format("Channel failed to send response message for user #%s",
                            request.getQuery())
            );
        }

        logger.info("Send user search request: {}", request.getQuery());
    }
}
