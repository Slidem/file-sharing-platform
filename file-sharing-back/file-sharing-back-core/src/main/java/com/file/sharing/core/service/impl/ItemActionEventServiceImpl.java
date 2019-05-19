package com.file.sharing.core.service.impl;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.actions.file.RenameFileAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.exception.FileExistsException;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.ItemActionType;
import com.file.sharing.core.service.ItemActionEventService;
import com.file.sharing.core.service.ItemActionEventSynchronization;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

import static com.file.sharing.core.objects.file.ItemActionType.*;
import static org.springframework.transaction.support.TransactionSynchronizationManager.registerSynchronization;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Service
@Transactional(readOnly = false)
public class ItemActionEventServiceImpl implements ItemActionEventService {

	private final Logger logger = LoggerFactory.getLogger(ItemActionEventServiceImpl.class);

	private final ItemsActionBusiness itemsActionBusiness;

	private final ItemActionJmsSender itemActionJmsSender;

	private final ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public ItemActionEventServiceImpl(ItemsActionBusiness itemsActionBusiness,
			ItemActionJmsSender itemActionJmsSender, ApplicationEventPublisher applicationEventPublisher) {
		this.itemsActionBusiness = itemsActionBusiness;
		this.itemActionJmsSender = itemActionJmsSender;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryCreated(CreateDirectoryAction action) {
		registerTransactionSynchronization(action, CREATE_DIRECTORY);
		DirectoryItem directoryItem = itemsActionBusiness.saveDirectoryItem(action);
		itemsActionBusiness.saveFileItemAction(directoryItem.getId(), CREATE_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryDeleted(DeleteDirectoryAction action) {
		registerTransactionSynchronization(action, DELETE_DIRECTORY);
		itemsActionBusiness.deleteItem(action.getItemId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), DELETE_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryRanamed(RenameDirectoryAction action) {
		registerTransactionSynchronization(action, RENAME_DIRECTORY);
		itemsActionBusiness.renameItem(action.getItemId(), action.getNewItemName());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), RENAME_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void directoryMoved(MoveDirectoryAction action) {
		registerTransactionSynchronization(action, MOVE_DIRECTORY);
		itemsActionBusiness.moveItem(action.getItemId(), action.getNewParentId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), MOVE_DIRECTORY);
	}

	@Override
	@Transactional(readOnly = false)
	public void fileUploaded(UploadFileAction action) {
		registerTransactionSynchronization(action, UPLOAD_FILE);
		File file = new File(action.getPath(), action.getItemName());
		if (!file.exists()) {
			try {
				FileUtils.writeByteArrayToFile(file, action.getBytes());
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
			}
		} else {
			throw new FileExistsException(action.getUserId(), action.getItemName());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void fileDeleted(DeleteFileAction action) {
		registerTransactionSynchronization(action, DELETE_FILE);
		itemsActionBusiness.deleteItem(action.getItemId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), DELETE_FILE);
	}

	@Override
	@Transactional(readOnly = false)
	public void fileRenamed(RenameFileAction action) {
		registerTransactionSynchronization(action, RENAME_FILE);
		itemsActionBusiness.renameItem(action.getItemId(), action.getNewItemName());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), RENAME_FILE);
	}

	@Override
	@Transactional(readOnly = false)
	public void fileMoved(MoveFileAction action) {
		registerTransactionSynchronization(action, MOVE_FILE);
		itemsActionBusiness.moveItem(action.getItemId(), action.getNewParentId());
		itemsActionBusiness.saveFileItemAction(action.getItemId(), MOVE_FILE);
	}

	// --------------------------------------------------------------------------------------------------

	private void registerTransactionSynchronization(ItemAction action, ItemActionType itemActionType) {
		registerSynchronization(new ItemActionEventSynchronization(action.getUserId(),
				action.getItemName(), itemActionType, itemActionJmsSender, applicationEventPublisher));
	}

}
