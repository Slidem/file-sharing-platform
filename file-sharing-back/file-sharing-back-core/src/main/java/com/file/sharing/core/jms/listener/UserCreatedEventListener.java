package com.file.sharing.core.jms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.jms.event.UserCreatedEvent;

@Component
public class UserCreatedEventListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@EventListener
	public void handleUserCreatedEvent(UserCreatedEvent event) {
		logger.info("User created event: {}", event);
	}
}
