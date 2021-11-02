package ru.balmukanov.telegram.adapter.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.balmukanov.telegram.application.api.PalantirBot;

@Component
public class PalantirBoImpl extends TelegramLongPollingBot implements PalantirBot {
	@Value("${bot.name}")
	private String botName;
	@Value("${bot.token}")
	private String token;

	@Override
	public void onUpdateReceived(Update update) {
		try {
			var message = new SendMessage();
			message.setChatId(String.valueOf(update.getMessage().getChatId()));
			message.setText("Hi");

			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return token;
	}
}