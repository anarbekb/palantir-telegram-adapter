package ru.balmukanov.telegram.adapter.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.balmukanov.telegram.adapter.telegram.command.HelpCommand;
import ru.balmukanov.telegram.adapter.telegram.command.StartCommand;
import ru.balmukanov.telegram.adapter.telegram.command.StartSearchUserCommand;
import ru.balmukanov.telegram.domain.User;

@Component
public class PalantirBot extends TelegramLongPollingCommandBot {
	@Value("${bot.name}")
	private String botName;
	@Value("${bot.token}")
	private String token;

	private final TextMessageHandler messageHandler;
	private final CheckUserHandler checkUserHandler;

	public PalantirBot(TextMessageHandler messageHandler, StartSearchUserCommand startSearchUserCommand,
	                   CheckUserHandler checkUserHandler, StartCommand startCommand, HelpCommand helpCommand) {
		this.messageHandler = messageHandler;
		this.checkUserHandler = checkUserHandler;

		register(helpCommand);
		register(startCommand);
		register(startSearchUserCommand);
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@SneakyThrows
	@Override
	public void processNonCommandUpdate(Update update) {
		User savedUser = checkUserHandler.handle(update.getMessage().getFrom());
		messageHandler.handle(update.getMessage(), savedUser);
	}

	@Override
	public String getBotToken() {
		return token;
	}
}