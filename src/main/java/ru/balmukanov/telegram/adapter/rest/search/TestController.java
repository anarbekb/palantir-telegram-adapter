package ru.balmukanov.telegram.adapter.rest.search;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.balmukanov.telegram.application.api.SearchUserRequestService;

@RestController
@RequestMapping("/api/rest/test")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
public class TestController {
    private final SearchUserRequestService searchUserRequestService;
    private final Mapper mapper;

    @PostMapping("/send/to/palantir")
    public String sendMessage(@RequestBody RequestDto request) {
        searchUserRequestService.startUserSearch(mapper.mapToDomain(request));
        return "OK";
    }
}