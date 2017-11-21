package com.file.sharing.core.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.jms.event.UserCreatedEvent;
import com.file.sharing.jms.commons.converter.JmsMessageConverter;
import com.file.sharing.jms.commons.object.JmsUserInfo;

@Component
public class JmsReceiver {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JmsMessageConverter jmsMessageConverter;

	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public JmsReceiver(JmsMessageConverter jmsMessageConverter, ApplicationEventPublisher applicationEventPublisher) {
		super();
		this.jmsMessageConverter = jmsMessageConverter;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@JmsListener(destination = "${user-info.topic}", containerFactory = "jmsListenerContainerFactory", subscription = "${user-info.topic}")
	public void receive(String message) {
		if (message == null) {
			return;
		}
		JmsUserInfo userInfo = jmsMessageConverter.toObject(message, JmsUserInfo.class);
		UserCreatedEvent userCreatedEvent = new UserCreatedEvent(userInfo.getId(), userInfo.getEmail());
		applicationEventPublisher.publishEvent(userCreatedEvent);
	}

}
