package com.file.sharing.core.jms;

import static com.file.sharing.core.objects.Constants.ITEM_ACTION_TOPIC_DEST;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.file.sharing.jms.commons.converter.JmsMessageConverter;
import com.file.sharing.jms.commons.object.ItemActionTransactionInfo;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
public class ItemActionJmsSender {

	private JmsSender jmsSender;

	private JmsMessageConverter jmsMessageConverter;

	private Destination destination;

	@Autowired
	public ItemActionJmsSender(JmsSender jmsSender, JmsMessageConverter jmsMessageConverter,
			@Qualifier(ITEM_ACTION_TOPIC_DEST) Destination destination) {
		this.jmsSender = jmsSender;
		this.jmsMessageConverter = jmsMessageConverter;
		this.destination = destination;
	}

	public void sendItemActionMessage(ItemActionTransactionInfo itemActionInfo) {
		String itemActionInfoMessage = jmsMessageConverter.toString(itemActionInfo);
		jmsSender.send(destination, itemActionInfoMessage);

	}
	
}
