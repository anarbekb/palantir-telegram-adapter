package ru.balmukanov.telegram.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.balmukanov.telegram.adapter.kafka.palantir.SearchUserRequestSender;

@RestController
@RequestMapping("/api/rest/test")
@RequiredArgsConstructor
public class TestController {
    private final SearchUserRequestSender searchUserRequestSender;

    @PostMapping("/send/to/palantir")
    public String sendMessage(@RequestBody RequestDto request) {
        searchUserRequestSender.send(request.getUserName());
        return "OK";
    }
}