package ru.balmukanov.telegram.domain;

public enum State {
	NEW("new"),
	ENTER_USERNAME("enter_username");

	State(String code) {
		this.code = code;
	}

	private String code;

	public String getCode() {
		return code;
	}
}