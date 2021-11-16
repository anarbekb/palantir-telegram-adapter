package ru.balmukanov.telegram.adapter.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckUserHandler {
	private final UserService userService;

	public User handle(User user) {
		try {
			return userService.get(user.getTelegramId());
		} catch (UserNotFoundException e) {
			log.info("Save user: " + user.getFirstName());
			return userService.save(user);
		}
	}
}