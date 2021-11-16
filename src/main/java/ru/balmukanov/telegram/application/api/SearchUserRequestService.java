package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.SearchResult;
import ru.balmukanov.telegram.domain.SearchUserRequest;

public interface SearchUserRequestService {
	void startUserSearch(SearchUserRequest request);
	void completeUserSearch(String correlationId, SearchResult searchResult);
}