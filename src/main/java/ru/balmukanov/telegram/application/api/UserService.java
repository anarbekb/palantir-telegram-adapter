package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.State;
import ru.balmukanov.telegram.domain.User;

public interface UserService {
	User get(Long telegramId) throws UserNotFoundException;
	void setState(Long userId, State state);
	User save(User user);
}