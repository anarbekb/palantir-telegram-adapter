package ru.balmukanov.telegram.adapter.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.balmukanov.telegram.application.api.UserInput;
import ru.balmukanov.telegram.application.api.exception.ServiceNotFoundException;
import ru.balmukanov.telegram.domain.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class TextMessageHandler {
	private final UserInputFactory inputFactory;

	public void handle(Message message, User user) {
		try {
			UserInput input = inputFactory.getInput(user);
			input.handle(user, message.getText());
		} catch (ServiceNotFoundException e) {
			log.warn("Input is null for user {} and state: {}", user.getId(), user.getState());
		}
	}
}