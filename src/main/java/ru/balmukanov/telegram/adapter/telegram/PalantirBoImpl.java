package ru.balmukanov.telegram.adapter.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.balmukanov.telegram.application.api.PalantirBot;

@Component
public class PalantirBoImpl extends TelegramLongPollingCommandBot implements PalantirBot {
	@Value("${bot.name}")
	private String botName;
	@Value("${bot.token}")
	private String token;

	public PalantirBoImpl() {
		register(new HelpCommand());
		register(new StartCommand());
		register(new SearchUserCommand());
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@SneakyThrows
	@Override
	public void processNonCommandUpdate(Update update) {
		var message = new SendMessage();
		message.setChatId(String.valueOf(update.getMessage().getChatId()));
		message.setText("Hello");

		execute(message);
	}

	@Override
	public String getBotToken() {
		return token;
	}
}