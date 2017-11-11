package com.file.sharing.config;

import static com.file.sharing.config.Constants.CREATE_USER_DEST_BEAN_NAME;
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

import com.file.sharing.jms.commons.repository.TopicsRespository;

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
	@Qualifier(value = CREATE_USER_DEST_BEAN_NAME)
	public Destination createUserTopicDestination(TopicsRespository topicsRepository) {
		return ActiveMQDestination.createDestination(topicsRepository.getUserTopic(), TOPIC_TYPE);
	}

}
