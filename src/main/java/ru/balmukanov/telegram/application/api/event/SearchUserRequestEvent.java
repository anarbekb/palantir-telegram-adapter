package ru.balmukanov.telegram.application.api.event;

import org.springframework.context.ApplicationEvent;

public class SearchUserRequestEvent extends ApplicationEvent {

    public SearchUserRequestEvent(String username) {
        super(username);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }
}