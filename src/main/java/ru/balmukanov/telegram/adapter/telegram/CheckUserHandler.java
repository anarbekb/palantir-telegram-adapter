package ru.balmukanov.telegram.adapter.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;

@Component
@Slf4j
public class CheckUserHandler {
	private final UserService userService;
	private final TelegramDtoMapper telegramDtoMapper;

	public CheckUserHandler(UserService userService) {
		this.userService = userService;
		this.telegramDtoMapper = new TelegramDtoMapper();
	}

	public ru.balmukanov.telegram.domain.User handle(User user) {
		ru.balmukanov.telegram.domain.User userMapped = telegramDtoMapper.mapToDto(user);
		try {
			return userService.getByTelegramId(userMapped.getTelegramId());
		} catch (UserNotFoundException e) {
			log.info("Save user: " + user.getFirstName());
			return userService.save(userMapped);
		}
	}
}