package com.file.sharing.core.jms.config;

import static com.file.sharing.core.objects.Constants.ITEM_ACTION_TOPIC_DEST;
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
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Configuration
public class JmsConfig {

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
	@Qualifier(value = ITEM_ACTION_TOPIC_DEST)
	public Destination itemActionTopicDestination(TopicsRespository topicsRepository) {
		return ActiveMQDestination.createDestination(topicsRepository.getItemActionTopic(), TOPIC_TYPE);
	}

}
