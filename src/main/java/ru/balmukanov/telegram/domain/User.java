package ru.balmukanov.telegram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long telegramId;
	private String firstName;
	private String userName;
	@Enumerated(EnumType.STRING)
	private State state;
}