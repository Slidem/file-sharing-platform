package com.file.sharing.core.jms;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
@Component
public class JmsSender {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final JmsTemplate jmsTemplate;

	@Autowired
	public JmsSender(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * @param destination topic/queue where the message will be puplished
	 * @param message the published message
	 */
	public void send(Destination destination, String message) {
		logger.info("Sending message {} to destination: {}", message, destination);
		jmsTemplate.convertAndSend(destination, message);
	}

}
