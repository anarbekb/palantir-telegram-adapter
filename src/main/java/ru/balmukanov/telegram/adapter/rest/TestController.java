package ru.balmukanov.telegram.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.balmukanov.telegram.adapter.kafka.palantir.Sender;

@RestController
@RequestMapping("/api/rest/test")
@RequiredArgsConstructor
public class TestController {
    private final Sender sender;

    @PostMapping("/send/to/palantir")
    public String sendMessage() {
        sender.send();
        return "OK";
    }
}