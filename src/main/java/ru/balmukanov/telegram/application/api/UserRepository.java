package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

public interface UserRepository {
	User getByTelegramId(Long id) throws UserNotFoundException;
	User getById(Long id) throws UserNotFoundException;
	User save(User user);
}