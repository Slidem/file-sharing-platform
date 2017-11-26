package com.file.sharing.core.service.impl;

import static com.file.sharing.core.objects.file.ItemActionType.CREATE_DIRECTORY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.ItemActionType;
import com.file.sharing.core.service.ItemActionEventSynchronization;
import com.file.sharing.core.service.ItemsActionEventService;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Service
@Transactional(readOnly = false)
public class ItemsActionEventServiceImpl implements ItemsActionEventService {


	private final ItemsActionBusiness itemsActionBusiness;

	private final ItemActionJmsSender itemActionJmsSender;


	@Autowired
	public ItemsActionEventServiceImpl(ItemsActionBusiness itemsActionBusiness,
			ItemActionJmsSender itemActionJmsSender) {
		this.itemsActionBusiness = itemsActionBusiness;
		this.itemActionJmsSender = itemActionJmsSender;
	}


	@Override
	@Transactional(readOnly = false)
	public void directoryCreated(CreateDirectoryAction action) {
		TransactionSynchronizationManager.registerSynchronization(new ItemActionEventSynchronization(action.getUserId(),
				action.getItemName(), ItemActionType.CREATE_DIRECTORY, itemActionJmsSender));


		DirectoryItem directoryItem = itemsActionBusiness.saveDirectoryItem(action);
		itemsActionBusiness.saveFileItemAction(directoryItem.getId(), CREATE_DIRECTORY);
	}


	@Override
	@Transactional(readOnly = false)
	public void directoryDeleted(DeleteDirectoryAction action) {
		TransactionSynchronizationManager.registerSynchronization(new ItemActionEventSynchronization(action.getUserId(),
				action.getItemName(), ItemActionType.DELETE_DIRECTORY, itemActionJmsSender));
		
		itemsActionBusiness.deleteItem(action.getItemId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), ItemActionType.DELETE_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryRanamed(RenameDirectoryAction action) {
		TransactionSynchronizationManager.registerSynchronization(new ItemActionEventSynchronization(action.getUserId(),
				action.getItemName(), ItemActionType.RENAME_DIRECTORY, itemActionJmsSender));

		itemsActionBusiness.renameItem(action.getItemId(), action.getNewItemName());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), ItemActionType.RENAME_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryMoved(MoveDirectoryAction action) {
		TransactionSynchronizationManager.registerSynchronization(new ItemActionEventSynchronization(action.getUserId(),
				action.getItemName(), ItemActionType.MOVE_DIRECTORY, itemActionJmsSender));

		itemsActionBusiness.moveItem(action.getItemId(), action.getNewParentId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), ItemActionType.MOVE_DIRECTORY);
	}

}
