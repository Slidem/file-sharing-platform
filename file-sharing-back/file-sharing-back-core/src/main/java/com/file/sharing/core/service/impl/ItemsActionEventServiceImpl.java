package com.file.sharing.core.service.impl;

import static com.file.sharing.core.objects.file.FileActionType.CREATE_DIRECTORY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.dao.UsersDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.jms.ItemActionJmsSender;
import com.file.sharing.core.objects.file.FileActionType;
import com.file.sharing.core.service.ItemActionEventSynchronization;
import com.file.sharing.core.service.ItemsActionEventService;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Service
@Transactional(readOnly = false)
public class ItemsActionEventServiceImpl implements ItemsActionEventService {

	private UsersDao usersDao;

	private ItemsActionBusiness itemsActionBusiness;

	private ItemActionJmsSender itemActionJmsSender;

	@Autowired
	public ItemsActionEventServiceImpl(UsersDao usersDao, ItemsActionBusiness itemsActionBusiness,
			ItemActionJmsSender itemActionJmsSender) {
		this.usersDao = usersDao;
		this.itemsActionBusiness = itemsActionBusiness;
		this.itemActionJmsSender = itemActionJmsSender;
	}


	@Override
	@Transactional(readOnly = false)
	public void directoryCreated(CreateDirectoryAction action) {

		Integer userId = action.getUserId();

		TransactionSynchronizationManager.registerSynchronization(new ItemActionEventSynchronization(userId,
				action.getItemName(), FileActionType.CREATE_DIRECTORY, itemActionJmsSender));

		User user = usersDao.find(userId).orElse(null);

		if (user == null) {
			throw new UserNotFoundException(userId);
		}

		DirectoryItem directoryItem = itemsActionBusiness.saveDirectoryItem(action, user);
		itemsActionBusiness.saveFileItemAction(directoryItem, CREATE_DIRECTORY);


	}


	@Override
	public void directoryDeleted(DeleteDirectoryAction action) {
		// TODO to be implemented
	}

}
