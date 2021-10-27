package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.SearchResult;

public interface SearchResultRepository {
	SearchResult save(SearchResult searchResult);
}