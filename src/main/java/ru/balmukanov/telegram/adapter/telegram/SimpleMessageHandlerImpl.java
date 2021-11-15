package ru.balmukanov.telegram.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.domain.User;

@Component
@RequiredArgsConstructor
public class SimpleMessageHandlerImpl {
	private final UserService userService;

	public void handle(User user) {
		if (!userService.userPresent(user.getTelegramId())) {
			userService.save(user);
		}
	}
}