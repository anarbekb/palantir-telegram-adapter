package ru.balmukanov.telegram.application.api;

import ru.balmukanov.telegram.domain.State;
import ru.balmukanov.telegram.domain.User;

public interface UserInput {
	void handle(User user, String userInput);
	State getState();
}