package ru.balmukanov.telegram.adapter.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.balmukanov.telegram.application.api.SearchResultRepository;
import ru.balmukanov.telegram.domain.SearchResult;

@Repository
@RequiredArgsConstructor
public class SearchResultJpaRepositoryAdapter implements SearchResultRepository {
	private final SearchResultJpaRepository searchResultRepository;
	@Override
	public SearchResult save(SearchResult searchResult) {
		return searchResultRepository.save(searchResult);
	}
}