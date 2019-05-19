package com.file.sharing.core.service;

import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.ItemActionStatus;
import com.file.sharing.core.objects.file.ItemActionType;
import com.file.sharing.jms.commons.object.ItemActionTransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import static com.file.sharing.core.objects.file.ItemActionStatus.FAILURE;
import static com.file.sharing.core.objects.file.ItemActionStatus.SUCCESS;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public class ItemActionEventSynchronization extends TransactionSynchronizationAdapter {

	private final int userId;

	private final String itemName;

	private final ItemActionJmsSender jmsSender;

	private final ItemActionType actionType;

	private ApplicationEventPublisher applicationEventPublisher;

	public ItemActionEventSynchronization(int userId, String itemName, ItemActionType actionType, ItemActionJmsSender jmsSender, ApplicationEventPublisher applicationEventPublisher) {
		this.userId = userId;
		this.itemName = itemName;
		this.jmsSender = jmsSender;
		this.actionType = actionType;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void afterCommit() {
		ItemActionTransactionInfo itemActionInfo = getActionInfo(SUCCESS);
		sendJmsMessage(itemActionInfo);
		publishEvent(itemActionInfo);
	}

	@Override
	public void afterCompletion(int status) {
		if (status != STATUS_COMMITTED) {
			sendJmsMessage(getActionInfo(FAILURE));
		}
	}

	private void sendJmsMessage(ItemActionTransactionInfo itemActionTransactionInfo) {
		jmsSender.sendItemActionMessage(itemActionTransactionInfo);
	}

	private void publishEvent(ItemActionTransactionInfo itemActionTransactionInfo) {
		applicationEventPublisher.publishEvent(itemActionTransactionInfo);
	}

	private ItemActionTransactionInfo getActionInfo(ItemActionStatus status) {
		
		return new ItemActionTransactionInfo.Builder()
				.withUserId(userId)
				.withItemName(itemName)
				.withStatus(status.name())
				.withItemAction(actionType.name())
				.build();
		
	}
}
