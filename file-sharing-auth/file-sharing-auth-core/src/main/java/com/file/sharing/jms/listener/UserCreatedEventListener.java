package com.file.sharing.jms.listener;

import static com.file.sharing.config.Constants.CREATE_USER_DEST_BEAN_NAME;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.jms.JmsSender;
import com.file.sharing.jms.commons.converter.JmsMessageConverter;
import com.file.sharing.jms.events.UserCreatedEvent;
import com.file.sharing.service.impl.FileSharingUserServiceImpl;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
@Component
public class UserCreatedEventListener {

	private final JmsSender jmsSender;

	private final Destination mqDestination;

	private final JmsMessageConverter jmsMessageConverter;

	@Autowired
	public UserCreatedEventListener(
			@Qualifier(CREATE_USER_DEST_BEAN_NAME) Destination userCreatedDestination,
			JmsSender jmsSender,
			JmsMessageConverter jmsMessageConverter) {

		this.jmsSender = jmsSender;
		this.mqDestination = userCreatedDestination;
		this.jmsMessageConverter = jmsMessageConverter;
	}

	/**
	 * Listens to an event of type {@link UserCreatedEvent} published by {@link ApplicationEventPublisher}
	 * @see also {@link FileSharingUserServiceImpl}
	 * 
	 * @param event published by {@link ApplicationEventPublisher}
	 */
	@EventListener
	public void handleUserCreatedEvent(UserCreatedEvent event) {
		String converterMessage = jmsMessageConverter.toString(event.getUserInfo());
		jmsSender.send(mqDestination, converterMessage);
	}

}
