package ru.balmukanov.telegram.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.balmukanov.telegram.application.api.*;
import ru.balmukanov.telegram.domain.SearchResult;
import ru.balmukanov.telegram.domain.SearchUserRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchUserRequestServiceImpl implements SearchUserRequestService {
	private final PalantirService palantirService;
	private final SearchUserRequestRepository requestRepository;
	private final SearchResultRepository resultRepository;
	private final UserService userService;
	private SearchResponse searchResponse;

	@Autowired
	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

	@Override
	@Transactional
	public void startUserSearch(SearchUserRequest request) {
		request.setCorrelationId(UUID.randomUUID().toString());
		request.setComplete(false);

		requestRepository.save(request);
		palantirService.send(request);
	}

	@Override
	@Transactional
	public void completeUserSearch(String correlationId, SearchResult searchResult) {
		SearchUserRequest request = requestRepository.getByCorrelationId(correlationId);

		resultRepository.save(searchResult);
		request.setSearchResult(searchResult);
		request.setComplete(true);

		searchResponse.send(request.getUser(), searchResult);

		userService.setWaitCommand(request.getUser());
	}
}