package ru.balmukanov.telegram.adapter.telegram.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.balmukanov.telegram.application.api.SearchUserRequestService;
import ru.balmukanov.telegram.application.api.UserInput;
import ru.balmukanov.telegram.domain.SearchUserRequest;
import ru.balmukanov.telegram.domain.State;
import ru.balmukanov.telegram.domain.User;

@Component
public class UserNameText implements UserInput {
	private SearchUserRequestService searchUserRequestService;

	@Autowired
	public void setSearchUserRequestService(SearchUserRequestService searchUserRequestService) {
		this.searchUserRequestService = searchUserRequestService;
	}

	@Override
	public void handle(User user, String userInput) {
		searchUserRequestService.startUserSearch(new SearchUserRequest(user, userInput.trim()));
	}

	@Override
	public State getState() {
		return State.ENTER_USERNAME;
	}
}