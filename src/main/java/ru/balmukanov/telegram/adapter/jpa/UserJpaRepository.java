package ru.balmukanov.telegram.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.balmukanov.telegram.domain.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
	Optional<User> findByTelegramId(Long id);
}