package ru.balmukanov.telegram.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.balmukanov.telegram.adapter.telegram.PalantirBot;
import ru.balmukanov.telegram.application.api.WishNotificationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishNotificationServiceImpl implements WishNotificationService {
	private final PalantirBot palantirBot;

	@Value("${bot.notification.wish.target}")
	private Long telegramId;

	@Override
	@Scheduled(cron = "0 10 * * *")
	public void goodMorning() {
		try {
			palantirBot.execute(sendMessage(telegramId, "Доброе утро, кошечка ♥️"));
			log.info("Send good morning for user: {}", telegramId);
		} catch (TelegramApiException e) {
			log.error("Fail send message to telegram: " + e.getMessage());
		}
	}

	@Override
	@Scheduled(cron = "0 00 * * *")
	public void goodNight() {
		try {
			palantirBot.execute(sendMessage(telegramId, "Доброй ночи, картошечка ♥️"));
			log.info("Send good night for user: {}", telegramId);
		} catch (TelegramApiException e) {
			log.error("Fail send message to telegram: " + e.getMessage());
		}
	}

	private SendMessage sendMessage(Long chatId, String text) {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(String.valueOf(chatId));
		message.setText(text);
		return message;
	}
}