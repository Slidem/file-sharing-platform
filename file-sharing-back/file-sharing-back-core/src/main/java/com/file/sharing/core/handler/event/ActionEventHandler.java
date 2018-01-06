package com.file.sharing.core.handler.event;

import static com.file.sharing.core.objects.file.ItemActionType.CREATE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.DELETE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.DELETE_FILE;
import static com.file.sharing.core.objects.file.ItemActionType.MOVE_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.MOVE_FILE;
import static com.file.sharing.core.objects.file.ItemActionType.RENAME_DIRECTORY;
import static com.file.sharing.core.objects.file.ItemActionType.RENAME_FILE;
import static com.file.sharing.core.objects.file.ItemActionType.UPLOAD_FILE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.actions.file.RenameFileAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.events.ItemActionEvent;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.ItemActionStatus;
import com.file.sharing.core.objects.file.ItemActionType;
import com.file.sharing.core.service.ItemActionEventService;
import com.file.sharing.jms.commons.object.JmsItemActionInfo;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
public class ActionEventHandler {

	private ItemActionEventService itemsActionEventService;

	private ItemActionJmsSender itemActionJmsSender;

	@Autowired
	public ActionEventHandler(ItemActionEventService itemsActionEventService,
			ItemActionJmsSender itemActionJmsSender) {
		this.itemsActionEventService = itemsActionEventService;
		this.itemActionJmsSender = itemActionJmsSender;
	}

	@EventListener
	public void handleCreateDirectoryActionEvent(ItemActionEvent<CreateDirectoryAction> createDirectoryActionEvent) {
		if (ItemActionStatus.SUCCESS != createDirectoryActionEvent.status()) {
			sendMessage(createDirectoryActionEvent, CREATE_DIRECTORY);
			return;
		}

		itemsActionEventService.directoryCreated(createDirectoryActionEvent.itemAction());
	}
	
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

	@EventListener
	public void handleUploadFileActionEvent(ItemActionEvent<UploadFileAction> uploadFileActionEvent) {
		if (ItemActionStatus.SUCCESS != uploadFileActionEvent.status()) {
			sendMessage(uploadFileActionEvent, UPLOAD_FILE);
			return;
		}

		itemsActionEventService.fileUploaded(uploadFileActionEvent.itemAction());
	}

	@EventListener
	public void handleDeleteFileActionEvent(ItemActionEvent<DeleteFileAction> deleteFileActionEvent) {
		if (ItemActionStatus.SUCCESS != deleteFileActionEvent.status()) {
			sendMessage(deleteFileActionEvent, DELETE_FILE);
			return;
		}

		itemsActionEventService.fileDeleted(deleteFileActionEvent.itemAction());
	}

	@EventListener
	public void handleRenameFileActionEvent(ItemActionEvent<RenameFileAction> renameFileActionEvent) {
		if (ItemActionStatus.SUCCESS != renameFileActionEvent.status()) {
			sendMessage(renameFileActionEvent, RENAME_FILE);
			return;
		}
		itemsActionEventService.fileRenamed(renameFileActionEvent.itemAction());
	}

	@EventListener
	public void handleMoveFileActionEvent(ItemActionEvent<MoveFileAction> moveFileActionEvent) {
		if (ItemActionStatus.SUCCESS != moveFileActionEvent.status()) {
			sendMessage(moveFileActionEvent, MOVE_FILE);
			return;
		}
		itemsActionEventService.fileMoved(moveFileActionEvent.itemAction());
	}

	// -------------------------------------------------------------------------------------------------

	private void sendMessage(ItemActionEvent<? extends ItemAction> itemActionEvent, ItemActionType type) {
		ItemAction itemAction = itemActionEvent.itemAction();
		
		JmsItemActionInfo itemInfo = new JmsItemActionInfo.Builder()
				.withItemAction(type.name())
				.withItemName(itemAction.getItemName())
				.withItemPath(itemAction.getPath())
				.withActionTime(itemAction.getActionTime())
				.withStatus(itemActionEvent.status().name())
				.withUserId(itemAction.getUserId())
				.build();
		
		itemActionJmsSender.sendItemActionMessage(itemInfo);
	}

}
