package ru.balmukanov.telegram.adapter.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.objects.Update;
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
	private final TelegramDtoMapper telegramDtoMapper;

	public PalantirBot(TextMessageHandler messageHandler, StartSearchUserCommand startSearchUserCommand,
	                   CheckUserHandler checkUserHandler, StartCommand startCommand) {
		this.telegramDtoMapper = new TelegramDtoMapper();
		this.messageHandler = messageHandler;
		this.checkUserHandler = checkUserHandler;

		register(new HelpCommand());
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
		User user = telegramDtoMapper.mapToDto(update.getMessage().getFrom());
		User savedUser = checkUserHandler.handle(user);
		messageHandler.handle(update.getMessage(), savedUser);
	}

	@Override
	public String getBotToken() {
		return token;
	}
}