package com.file.sharing.core.service;

import static com.file.sharing.core.objects.file.FileActionStatus.FAILURE;
import static com.file.sharing.core.objects.file.FileActionStatus.SUCCESS;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.FileActionStatus;
import com.file.sharing.core.objects.file.FileActionType;
import com.file.sharing.jms.commons.object.JmsItemActionInfo;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public class ItemActionEventSynchronization extends TransactionSynchronizationAdapter {

	private final int userId;

	private final String itemName;

	private final ItemActionJmsSender jmsSender;

	private final FileActionType actionType;

	public ItemActionEventSynchronization(int userId, String itemName, FileActionType actionType, ItemActionJmsSender jmsSender) {
		this.userId = userId;
		this.itemName = itemName;
		this.jmsSender = jmsSender;
		this.actionType = actionType;
	}

	@Override
	public void afterCommit() {
		sendMessage(SUCCESS);
	}

	@Override
	public void afterCompletion(int status) {
		if (status != STATUS_COMMITTED) {
			sendMessage(FAILURE);
		}
	}

	private void sendMessage(FileActionStatus status) {
		jmsSender.sendItemActionMessage(getActionInfo(status));
	}

	private JmsItemActionInfo getActionInfo(FileActionStatus status) {
		
		return new JmsItemActionInfo.Builder()
				.withUserId(userId)
				.withItemName(itemName)
				.withStatus(status.name())
				.withItemAction(actionType.name())
				.build();
		
	}
}
