package ru.balmukanov.telegram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
	@OneToMany(mappedBy = "user")
	private List<SearchUserRequest> requests;
}