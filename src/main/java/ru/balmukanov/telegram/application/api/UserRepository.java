package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

public interface UserRepository {
	User getByTelegramId(Long id) throws UserNotFoundException;

	void save(User user);
}