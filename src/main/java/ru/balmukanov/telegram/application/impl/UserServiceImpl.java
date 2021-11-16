package ru.balmukanov.telegram.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.balmukanov.telegram.application.api.UserRepository;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.State;
import ru.balmukanov.telegram.domain.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public User get(Long telegramId) throws UserNotFoundException {
		return userRepository.getByTelegramId(telegramId);
	}

	@Override
	@Transactional
	public void setEnterUserName(User user) {
		try {
			user = userRepository.getByTelegramId(user.getTelegramId());
			user.setState(State.ENTER_USERNAME);
		} catch (UserNotFoundException e) {
			log.error("Error set state: " + State.ENTER_USERNAME);
		}
	}


	@Override
	@Transactional
	public void setWaitCommand(User user) {
		try {
			user = userRepository.getByTelegramId(user.getTelegramId());
			user.setState(State.WAIT_COMMAND);
		} catch (UserNotFoundException e) {
			log.error("Error set state: " + State.WAIT_COMMAND);
		}
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
}