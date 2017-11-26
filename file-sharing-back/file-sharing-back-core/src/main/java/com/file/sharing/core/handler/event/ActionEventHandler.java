package com.file.sharing.core.handler.event;

import static com.file.sharing.core.objects.file.ItemActionType.CREATE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.DELETE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.MOVE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.RENAME_DIRECTORY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.events.ItemActionEvent;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.ItemActionStatus;
import com.file.sharing.core.objects.file.ItemActionType;
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
		if (ItemActionStatus.SUCCESS != createDirectoryActionEvent.status()) {
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
		if (ItemActionStatus.SUCCESS != createDirectoryActionEvent.status()) {
			sendMessage(createDirectoryActionEvent, DELETE_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryDeleted(createDirectoryActionEvent.itemAction());
	}
	
	@EventListener
	public void handleRenameDirectoryActionEvent(ItemActionEvent<RenameDirectoryAction> renameDirectoryActionEvent) {
		if (ItemActionStatus.SUCCESS != renameDirectoryActionEvent.status()) {
			sendMessage(renameDirectoryActionEvent, RENAME_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryRanamed(renameDirectoryActionEvent.itemAction());
	}

	@EventListener
	public void handleMoveDirectoryActionEvent(ItemActionEvent<MoveDirectoryAction> moveDirectoryActionEvent) {
		if (ItemActionStatus.SUCCESS != moveDirectoryActionEvent.status()) {
			sendMessage(moveDirectoryActionEvent, MOVE_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryMoved(moveDirectoryActionEvent.itemAction());
	}

	private void sendMessage(ItemActionEvent<? extends ItemAction> itemActionEvent, ItemActionType type) {
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
