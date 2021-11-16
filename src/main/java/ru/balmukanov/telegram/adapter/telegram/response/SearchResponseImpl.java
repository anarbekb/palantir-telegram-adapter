package ru.balmukanov.telegram.adapter.telegram.response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.balmukanov.telegram.adapter.telegram.PalantirBot;
import ru.balmukanov.telegram.application.api.SearchResponse;
import ru.balmukanov.telegram.domain.SearchResult;
import ru.balmukanov.telegram.domain.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchResponseImpl implements SearchResponse {
	private final PalantirBot palantirBot;

	public void send(User user, SearchResult result) {
		try {
			palantirBot.execute(getMessage(String.valueOf(user.getTelegramId()), getMessageText(result)));
		} catch (TelegramApiException e) {
			log.error("Fail send response: " + e.getMessage());
		}
	}

	private SendMessage getMessage(String chatId, String text) {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(chatId);
		message.setText(text);

		return message;
	}

	private String getMessageText(SearchResult result) {
		if (result.isFind()) {
			StringBuilder builder = new StringBuilder("Нашлось " + result.getFinds().size() + " рез.:\n");
			result.getFinds().entrySet().forEach(entry -> builder.append(entry.getKey()).append(" - ").append(entry.getValue()));
			return builder.toString();
		} else {
			return "По введенному запросу ничего не нашлось";
		}
	}
}