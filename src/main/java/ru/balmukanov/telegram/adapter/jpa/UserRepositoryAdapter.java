package ru.balmukanov.telegram.adapter.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.balmukanov.telegram.application.api.UserRepository;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
	private final UserJpaRepository userJpaRepository;

	@Override
	public User getByTelegramId(Long id) throws UserNotFoundException {
		return userJpaRepository.findByTelegramId(id).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}