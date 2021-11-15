package ru.balmukanov.telegram.adapter.telegram.command;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;

@Component
public class StartSearchUserCommand extends BotCommand {
	private final UserService userService;
	public StartSearchUserCommand(UserService userService) {
		super("search", "Поиск пользователя по нику");
		this.userService = userService;
	}

	@SneakyThrows
	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		try {
			ru.balmukanov.telegram.domain.User domainUser = userService.get(user.getId());
			userService.setEnterUserName(domainUser);
			sendInputMessage(absSender, chat.getId());
		} catch (UserNotFoundException e) {
			sendError(absSender, chat.getId());
		}
	}

	private void sendError(AbsSender absSender, Long chatId) throws TelegramApiException {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(String.valueOf(chatId));
		message.setText("Ошибка, попробуйте позже");
		absSender.execute(message);
	}

	private void sendInputMessage(AbsSender absSender, Long chatId) throws TelegramApiException {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(String.valueOf(chatId));
		message.setText("Введите имя пользователя:");
		absSender.execute(message);
	}
}