package ru.balmukanov.telegram.adapter.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.balmukanov.telegram.application.api.PalantirBot;

@Component
public class PalantirBoImpl extends TelegramLongPollingCommandBot implements PalantirBot {
	@Value("${bot.name}")
	private String botName;
	@Value("${bot.token}")
	private String token;

	public PalantirBoImpl() {
		register(new HelpCommand());
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public void processNonCommandUpdate(Update update) {
		setAnswer(update.getChannelPost().getChatId(), "Hello");
	}

	@Override
	public String getBotToken() {
		return token;
	}

	private void setAnswer(Long chatId, String text) {
		SendMessage answer = new SendMessage();
		answer.setText(text);
		answer.setChatId(chatId.toString());
		try {
			execute(answer);
		} catch (TelegramApiException e) {
			//логируем сбой Telegram Bot API, используя userName
		}
	}
}