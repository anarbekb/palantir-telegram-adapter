package ru.balmukanov.telegram.adapter.telegram.command;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.balmukanov.telegram.adapter.telegram.CheckUserHandler;
import ru.balmukanov.telegram.application.api.UserService;

import static ru.balmukanov.telegram.domain.State.WAIT_COMMAND;

@Component
public class StartCommand extends BotCommand {
	private final UserService userService;
	private final CheckUserHandler checkUserHandler;

	public StartCommand(UserService userService, CheckUserHandler userHandler) {
		super("start", "Запуск бота");

		this.userService = userService;
		this.checkUserHandler = userHandler;
	}

	@SneakyThrows
	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		checkUserHandler.handle(user);
		userService.setStateByTelegramId(user.getId(), WAIT_COMMAND);

		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(String.valueOf(chat.getId()));
		message.setText("Go! Для помощи, нажмите /help");
		absSender.execute(message);
	}
}