package ru.balmukanov.telegram.adapter.kafka.palantir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.balmukanov.telegram.adapter.kafka.ChannelBinding;
import ru.balmukanov.telegram.application.api.PalantirService;
import ru.balmukanov.telegram.domain.SearchUserRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PalantirServiceAdapter implements PalantirService {
    private final ChannelBinding channelBinding;
    private final PalantirDtoMapper mapper;

    public void send(SearchUserRequest searchRequest) {
        SearchUserRequestDto request = mapper.mapToDto(searchRequest);

        boolean published = channelBinding.searchUserRequest().send(MessageBuilder.withPayload(request).build());
        if (!published) {
            throw new MessageDeliveryException(
                    String.format("Channel failed to send response message for user #%s",
                            request.getQuery())
            );
        }

        log.info("Send user search request: {}", request.getQuery());
    }
}
