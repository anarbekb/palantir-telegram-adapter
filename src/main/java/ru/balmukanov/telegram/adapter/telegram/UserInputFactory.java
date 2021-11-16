package ru.balmukanov.telegram.adapter.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.UserInput;
import ru.balmukanov.telegram.application.api.exception.ServiceNotFoundException;
import ru.balmukanov.telegram.domain.State;
import ru.balmukanov.telegram.domain.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserInputFactory {
	private final List<UserInput> implementations;
	private Map<State, UserInput> implementationsMap;

	@PostConstruct
	public void init() {
		for (UserInput input : implementations) {
			implementationsMap.put(input.getState(), input);
		}
	}

	public UserInput getInput(User user) throws ServiceNotFoundException {
		UserInput input = implementationsMap.get(user.getState());
		if (input == null) {
			throw new ServiceNotFoundException();
		}
		return input;
	}
}