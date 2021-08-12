package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;

@Service
@RequiredArgsConstructor
public class Sender {
    private final ChannelBinding channelBinding;

    public void send() {
        var request = new SearchUserRequestDto("test");

        boolean published = channelBinding.searchUserRequest().send(MessageBuilder.withPayload(request).build());
        if (!published) {
            throw new MessageDeliveryException(
                    String.format("Channel failed to send response message for user #%s",
                            request.getQuery())
            );
        }
    }
}
