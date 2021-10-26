package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.SearchUserRequest;

public interface SearchUserRequestRepository {
	SearchUserRequest save(SearchUserRequest request);

	SearchUserRequest getByCorrelationId(String correlationId);
}