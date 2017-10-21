package com.file.sharing.jms;

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
	 * @param destination
	 * @param message
	 */
	public void send(Destination destination, Object message) {
		logger.info("Sending message {} to destination: {}", message, destination);
		jmsTemplate.convertAndSend(destination, message);
	}

}
