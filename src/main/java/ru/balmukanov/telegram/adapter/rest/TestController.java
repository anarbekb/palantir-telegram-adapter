package ru.balmukanov.telegram.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.balmukanov.telegram.application.api.event.SearchUserRequestEvent;

@RestController
@RequestMapping("/api/rest/test")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
public class TestController {
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/send/to/palantir")
    public String sendMessage(@RequestBody RequestDto request) {
        eventPublisher.publishEvent(new SearchUserRequestEvent(request.getUsername()));
        return "OK";
    }
}