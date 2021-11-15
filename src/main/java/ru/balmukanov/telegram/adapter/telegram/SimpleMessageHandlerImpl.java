package ru.balmukanov.telegram.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.balmukanov.telegram.adapter.telegram.input.UserNameInput;
import ru.balmukanov.telegram.application.api.UserInput;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

import java.util.List;

import static ru.balmukanov.telegram.domain.State.ENTER_USERNAME;

@Component
@RequiredArgsConstructor
public class SimpleMessageHandlerImpl {
	private final UserService userService;
	private final List<UserInput> userInputImplementations;

	public void handle(Message message, User user) {
		try {
			User savedUser = userService.get(user.getTelegramId());
			handleInput(message, savedUser);
		} catch (UserNotFoundException e) {
			userService.save(user);
		}
	}

	private void handleInput(Message message, User user) {
		UserInput input = getInput(user);
		if (input != null) {
			input.handle(user, message.getText());
		}
	}

	private UserInput getInput(User user) {
		if (user.getState() == ENTER_USERNAME) {
			return userInputImplementations.stream()
				.filter(userInput -> userInput instanceof UserNameInput)
				.findFirst().orElse(null);
		}

		return null;
	}
}