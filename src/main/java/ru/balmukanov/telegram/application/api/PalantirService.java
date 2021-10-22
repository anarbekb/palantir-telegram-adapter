package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.SearchUserRequest;

public interface PalantirService {
    void send(SearchUserRequest searchRequest);
}