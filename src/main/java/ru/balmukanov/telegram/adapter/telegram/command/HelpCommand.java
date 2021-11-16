package ru.balmukanov.telegram.adapter.telegram.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.balmukanov.telegram.application.api.UserService;

import static ru.balmukanov.telegram.domain.State.WAIT_COMMAND;

@Component
public class HelpCommand extends org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand {
	private final UserService userService;

	private static final String COMMAND_IDENTIFIER = "help";
	private static final String COMMAND_DESCRIPTION = "показать все команды. Используй /help [имя команды] для подробной информации";
	private static final String EXTENDED_DESCRIPTION = "Эта команда отображает все команды данного бота.\n /help [имя команды] покажет дополнительную информацию по выбранной команде";

	public HelpCommand(UserService userService) {
		super(COMMAND_IDENTIFIER, COMMAND_DESCRIPTION, EXTENDED_DESCRIPTION);

		this.userService = userService;
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		userService.setStateByTelegramId(user.getId(), WAIT_COMMAND);
		super.execute(absSender, user, chat, arguments);
	}
}