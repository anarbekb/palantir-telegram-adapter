package ru.balmukanov.telegram.adapter.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.balmukanov.telegram.adapter.telegram.command.StartCommand;
import ru.balmukanov.telegram.adapter.telegram.command.StartSearchUserCommand;
import ru.balmukanov.telegram.application.api.PalantirBot;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.domain.User;

@Component
public class PalantirBoImpl extends TelegramLongPollingCommandBot implements PalantirBot {
	@Value("${bot.name}")
	private String botName;
	@Value("${bot.token}")
	private String token;

	private final SimpleMessageHandlerImpl messageHandler;
	private final TelegramDtoMapper telegramDtoMapper;

	public PalantirBoImpl(SimpleMessageHandlerImpl messageHandler, UserService userService) {
		this.telegramDtoMapper = new TelegramDtoMapper();
		this.messageHandler = messageHandler;

		register(new HelpCommand());
		register(new StartCommand());
		register(new StartSearchUserCommand(userService));
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@SneakyThrows
	@Override
	public void processNonCommandUpdate(Update update) {
		User user = telegramDtoMapper.mapToDto(update.getMessage().getFrom());
		messageHandler.handle(update.getMessage(), user);
	}

	@Override
	public String getBotToken() {
		return token;
	}
}