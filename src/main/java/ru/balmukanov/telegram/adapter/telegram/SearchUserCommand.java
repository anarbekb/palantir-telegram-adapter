package ru.balmukanov.telegram.adapter.telegram;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class SearchUserCommand extends BotCommand {

	public SearchUserCommand() {
		super("search", "Поиск пользователя по нику");
	}

	@SneakyThrows
	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		SendMessage message = new SendMessage();
		message.enableMarkdown(true);
		message.setChatId(String.valueOf(chat.getId()));
		message.setText("Введите имя пользователя:");
		absSender.execute(message);
	}
}