package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.User;

public interface UserService {
	boolean userPresent(Long telegramId);
	void save(User user);
}