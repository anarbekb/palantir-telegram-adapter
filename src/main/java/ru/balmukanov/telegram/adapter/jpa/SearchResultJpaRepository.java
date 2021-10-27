package ru.balmukanov.telegram.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.telegram.domain.SearchResult;

public interface SearchResultJpaRepository extends JpaRepository<SearchResult, Long> {
}