package com.file.sharing.config;

import static org.apache.activemq.command.ActiveMQDestination.TOPIC_TYPE;

import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 *
 * 
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 * 
 *          <p>
 *          ActiveMQ configuration
 *          </p>
 */
@Configuration
public class AuthJmsConfig {

	@Value("${activemq.broker-url}")
	private String brokerUrl;

	@Value("${topic.create-user}")
	private String createUserTopic;

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);

		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory(activeMQConnectionFactory());
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(cachingConnectionFactory());
	}

	@Bean
	@Qualifier(value = "createUserTopicDestination")
	public Destination createUserTopicDestination() {
		return ActiveMQDestination.createDestination(createUserTopic, TOPIC_TYPE);
	}

}
