package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

public interface UserService {
	User get(Long telegramId) throws UserNotFoundException;
	void setEnterUserName(User user);
	void save(User user);
}