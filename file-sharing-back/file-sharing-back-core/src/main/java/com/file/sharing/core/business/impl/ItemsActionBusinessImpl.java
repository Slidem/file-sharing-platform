package com.file.sharing.core.business.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.business.FileItemCategoryBusiness;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.dao.DirectoryItemDao;
import com.file.sharing.core.dao.FileItemDao;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.dao.UsersDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.entity.ItemActionEntity;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.objects.file.ItemActionType;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
public class ItemsActionBusinessImpl implements ItemsActionBusiness {

	private DirectoryItemDao directoryItemDao;

	private ItemActionDao itemActionDao;

	private ItemDao itemDao;

	private UsersDao usersDao;

	private FileItemCategoryBusiness fileItemCategoryBusiness;

	private FileItemDao fileItemDao;


	@Autowired
	public ItemsActionBusinessImpl(DirectoryItemDao directoryItemDao, ItemActionDao itemActionDao, ItemDao itemDao,
			UsersDao usersDao, FileItemCategoryBusiness fileItemCategoryBusiness, FileItemDao fileItemDao) {
		this.directoryItemDao = directoryItemDao;
		this.itemActionDao = itemActionDao;
		this.itemDao = itemDao;
		this.usersDao = usersDao;
		this.fileItemCategoryBusiness = fileItemCategoryBusiness;
		this.fileItemDao = fileItemDao;
	}

	@Override
	public DirectoryItem saveDirectoryItem(CreateDirectoryAction action) {

		User user = usersDao.find(action.getUserId()).orElse(null);

		if (user == null) {
			throw new UserNotFoundException(action.getUserId());
		}

		DirectoryItem directoryItem = new DirectoryItem();

		directoryItem.setName(action.getItemName());
		directoryItem.setPath(action.getPath());
		directoryItem.setUser(user);

		action.getParentId().ifPresent(id -> directoryItem.setParent(getParent(id)));

		directoryItemDao.save(directoryItem);
		directoryItemDao.flush();

		return directoryItem;
	}

	@Override
	public void deleteItem(Integer itemId) {
		Optional<Item> item = itemDao.find(itemId);
		item.ifPresent(itemDao::delete);
	}

	@Override
	public void renameItem(Integer itemId, String itemName) {
		Optional<Item> item = itemDao.find(itemId);
		item.ifPresent(i -> {
			i.setName(itemName);
			itemDao.save(i);
		});
	}

	@Override
	public void moveItem(Integer itemId, Integer newParentId) {
		Optional<DirectoryItem> newParent = directoryItemDao.find(newParentId);
		Optional<Item> item = itemDao.find(itemId);
		item.ifPresent(i -> {
			i.setParent(newParent.orElse(null));
			itemDao.save(i);

		});
	}

	@Override
	public void saveFileItemAction(Integer itemId, ItemActionType actionType) {
		ItemActionEntity itemAction = new ItemActionEntity();
		itemAction.setActionTime(Timestamp.from(Instant.now()));
		itemAction.setActionType(actionType);
		itemAction.setItemId(itemId);

		itemActionDao.save(itemAction);
	}

	@Override
	public FileItem saveFileItem(UploadFileAction uploadAction) {
		String fileName = uploadAction.getItemName();

		FileItem fileItem = new FileItem();
		
		FileItemCategory fileItemCategory = fileItemCategoryBusiness.getFileItemCategoryFromItemName(fileName);
		
		fileItem.setCategory(fileItemCategory);
		fileItem.setName(uploadAction.getItemName());
		fileItem.setParent(directoryItemDao.find(uploadAction.getParentId()).orElse(null));
		fileItem.setPath(uploadAction.getPath());
		fileItem.setUploadTime(Timestamp.from(Instant.now()));
		fileItem.setUser(usersDao.find(uploadAction.getUserId()).orElse(null));
		
		fileItemDao.save(fileItem);
		fileItemDao.flush();
		return fileItem;
	}

	private DirectoryItem getParent(Integer parentId) {
		return directoryItemDao.find(parentId).orElse(null);
	}

}
