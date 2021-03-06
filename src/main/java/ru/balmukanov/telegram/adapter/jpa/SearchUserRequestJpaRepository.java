package ru.balmukanov.telegram.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.telegram.domain.SearchUserRequest;

public interface SearchUserRequestJpaRepository extends JpaRepository<SearchUserRequest, Long> {
	SearchUserRequest findByCorrelationId(String correlationId);
}