package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.SearchResult;
import ru.balmukanov.telegram.domain.User;

public interface SearchResponse {
	void send(User user, SearchResult result);
}