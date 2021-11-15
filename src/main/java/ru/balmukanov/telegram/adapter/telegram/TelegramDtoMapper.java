package ru.balmukanov.telegram.adapter.telegram;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.balmukanov.telegram.application.api.exception.AdapterMappingException;
import ru.balmukanov.telegram.domain.State;

public class TelegramDtoMapper {
	private final ModelMapper mapper;

	public TelegramDtoMapper() {
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		this.mapper.typeMap(User.class, ru.balmukanov.telegram.domain.User.class)
			.addMapping(User::getId, ru.balmukanov.telegram.domain.User::setTelegramId)
			.addMapping(User::getFirstName, ru.balmukanov.telegram.domain.User::setFirstName)
			.addMapping(User::getUserName, ru.balmukanov.telegram.domain.User::setUserName)
			.addMapping(user -> State.NEW, ru.balmukanov.telegram.domain.User::setState);
	}

	public ru.balmukanov.telegram.domain.User mapToDto(User user) {
		try {
			return mapper.map(user, ru.balmukanov.telegram.domain.User.class);
		} catch (Exception e) {
			throw new AdapterMappingException(e);
		}
	}
}