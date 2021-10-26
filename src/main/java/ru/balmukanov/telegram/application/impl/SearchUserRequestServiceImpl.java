package ru.balmukanov.telegram.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.balmukanov.telegram.application.api.PalantirService;
import ru.balmukanov.telegram.application.api.SearchUserRequestRepository;
import ru.balmukanov.telegram.application.api.SearchUserRequestService;
import ru.balmukanov.telegram.domain.SearchUserRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchUserRequestServiceImpl implements SearchUserRequestService {
	private final PalantirService palantirService;
	private final SearchUserRequestRepository requestRepository;

	@Override
	@Transactional
	public void startUserSearch(SearchUserRequest request) {
		request.setCorrelationId(UUID.randomUUID().toString());
		request.setComplete(false);

		requestRepository.save(request);
		palantirService.send(request);
	}

	@Override
	public void completeUserSearch(String correlationId) {
		SearchUserRequest request = requestRepository.getByCorrelationId(correlationId);
		request.setComplete(true);
	}
}