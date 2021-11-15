package ru.balmukanov.telegram.adapter.telegram.input;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.SearchUserRequestService;
import ru.balmukanov.telegram.application.api.UserInput;
import ru.balmukanov.telegram.domain.SearchUserRequest;
import ru.balmukanov.telegram.domain.User;

@Component
@RequiredArgsConstructor
public class UserNameInput implements UserInput {
	private final SearchUserRequestService searchUserRequestService;

	@Override
	public void handle(User user, String userInput) {
		searchUserRequestService.startUserSearch(new SearchUserRequest(user, userInput.trim()));
	}
}