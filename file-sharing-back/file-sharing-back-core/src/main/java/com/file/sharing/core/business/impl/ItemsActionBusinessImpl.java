package com.file.sharing.core.business.impl;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.dao.DirectoryItemDao;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.entity.ItemAction;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.objects.ActionType;
import com.file.sharing.core.objects.file.FileActionType;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
public class ItemsActionBusinessImpl implements ItemsActionBusiness {

	private DirectoryItemDao directoryItemDao;

	private ItemActionDao itemActionDao;

	@Autowired
	public ItemsActionBusinessImpl(DirectoryItemDao directoryItemDao, ItemActionDao itemActionDao) {
		this.directoryItemDao = directoryItemDao;
		this.itemActionDao = itemActionDao;
	}

	@Override
	public DirectoryItem saveDirectoryItem(CreateDirectoryAction action, User user) {

		DirectoryItem directoryItem = new DirectoryItem();

		directoryItem.setName(action.getItemName());
		directoryItem.setPath(action.getPath());
		directoryItem.setUser(user);

		directoryItemDao.save(directoryItem);
		directoryItemDao.flush();
		return directoryItem;
	}

	@Override
	public void saveFileItemAction(Item item, FileActionType actionType) {
		ItemAction itemAction = new ItemAction();
		itemAction.setActionTime(Timestamp.from(Instant.now()));
		itemAction.setActionType(ActionType.CREATE);
		itemAction.setItemId(item.getId());

		itemActionDao.save(itemAction);
	}

}
