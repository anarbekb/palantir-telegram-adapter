package ru.balmukanov.telegram.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.balmukanov.telegram.application.api.UserRepository;
import ru.balmukanov.telegram.application.api.UserService;
import ru.balmukanov.telegram.application.api.exception.UserNotFoundException;
import ru.balmukanov.telegram.domain.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public boolean userPresent(Long telegramId) {
		try {
			userRepository.getByTelegramId(telegramId);
			return true;
		} catch (UserNotFoundException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}
}