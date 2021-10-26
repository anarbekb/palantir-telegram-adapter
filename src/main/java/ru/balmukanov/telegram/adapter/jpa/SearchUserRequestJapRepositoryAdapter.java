package ru.balmukanov.telegram.adapter.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.balmukanov.telegram.application.api.SearchUserRequestRepository;
import ru.balmukanov.telegram.domain.SearchUserRequest;

@Repository
@RequiredArgsConstructor
public class SearchUserRequestJapRepositoryAdapter implements SearchUserRequestRepository {
	private final SearchUserRequestJpaRepository requestRepository;
	@Override
	public SearchUserRequest save(SearchUserRequest request) {
		return requestRepository.save(request);
	}

	@Override
	public SearchUserRequest getByCorrelationId(String correlationId) {
		return requestRepository.findByCorrelationId(correlationId);
	}
}