package com.file.sharing.core.handler.event;

import static com.file.sharing.core.objects.file.FileActionType.CREATE_DIRECTORY;
import static com.file.sharing.core.objects.file.FileActionType.DELETE_DIRECTORY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.events.ItemActionEvent;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.FileActionStatus;
import com.file.sharing.core.objects.file.FileActionType;
import com.file.sharing.core.service.ItemsActionEventService;
import com.file.sharing.jms.commons.object.JmsItemActionInfo;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
public class ActionEventHandler {

	private ItemsActionEventService itemsActionEventService;

	private ItemActionJmsSender itemActionJmsSender;

	@Autowired
	public ActionEventHandler(ItemsActionEventService itemsActionEventService,
			ItemActionJmsSender itemActionJmsSender) {
		this.itemsActionEventService = itemsActionEventService;
		this.itemActionJmsSender = itemActionJmsSender;
	}

	/**
	 * @param createDirectoryAction
	 */
	@EventListener
	public void handleCreateDirectoryActionEvent(ItemActionEvent<CreateDirectoryAction> createDirectoryActionEvent) {
		if (FileActionStatus.SUCCESS != createDirectoryActionEvent.status()) {
			sendMessage(createDirectoryActionEvent, CREATE_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryCreated(createDirectoryActionEvent.itemAction());
	}
	
	/**
	 * @param createDirectoryAction
	 */
	@EventListener
	public void handleDeleteDirectoryActionEvent(ItemActionEvent<DeleteDirectoryAction> createDirectoryActionEvent) {
		if (FileActionStatus.SUCCESS != createDirectoryActionEvent.status()) {
			sendMessage(createDirectoryActionEvent, DELETE_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryDeleted(createDirectoryActionEvent.itemAction());
	}
	

	private void sendMessage(ItemActionEvent<? extends ItemAction> itemActionEvent, FileActionType type) {
		ItemAction itemAction = itemActionEvent.itemAction();
		
		JmsItemActionInfo itemInfo = new JmsItemActionInfo.Builder()
				.withItemAction(type.name())
				.withItemName(itemAction.getItemName())
				.withStatus(itemActionEvent.status().name())
				.withUserId(itemAction.getUserId())
				.build();
		
		itemActionJmsSender.sendItemActionMessage(itemInfo);
	}

}
