package com.file.sharing.core.business.impl;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.business.FileItemCategoryBusiness;
import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.dao.*;
import com.file.sharing.core.entity.*;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.objects.file.ItemActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static com.file.sharing.core.objects.Constants.DUPLICATE_SUFFIX_STRING;
import static com.file.sharing.core.objects.Constants.DUPLICATE_SUFFIX_STRING_PATTERN;

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
		DirectoryItem newParent = getParent(newParentId);
		Optional<Item> item = itemDao.find(itemId);
		item.ifPresent(i -> {
			i.setParent(newParent);
			itemDao.save(i);

		});
	}

	@Override
	@Transactional
	public void saveFileItemAction(Integer itemId, ItemActionType actionType) {
		ItemActionEntity itemAction = new ItemActionEntity();
		itemAction.setActionTime(Timestamp.from(Instant.now()));
		itemAction.setActionType(actionType);
		itemAction.setItemId(itemId);

		itemActionDao.save(itemAction);
	}

	@Override
	@Transactional
	public FileItem saveFileItem(UploadFileAction uploadAction) {
		String fileName = uploadAction.getItemName();

		FileItem fileItem = new FileItem();

		FileItemCategory fileItemCategory = fileItemCategoryBusiness.getFileItemCategoryFromItemName(fileName);

		fileItem.setCategory(fileItemCategory);
		fileItem.setName(uploadAction.getItemName());
		fileItem.setParent(getParent(uploadAction.getParentId()));
		fileItem.setPath(uploadAction.getPath());
		fileItem.setUploadTime(Timestamp.from(Instant.now()));
		fileItem.setUser(usersDao.find(uploadAction.getUserId()).orElse(null));
		fileItem.setSize(uploadAction.getSize());

		if(fileItemDao.exists(fileItem)) {
			List<String> fileItems = fileItemDao.getSimilarFileItemsFromSameDirectory(fileItem);
			fileItem.setName(getDuplicateNameWithSuffix(fileItem.getName(), getNextAvailableSuffix(fileItems)));
		}

		fileItemDao.save(fileItem);
		fileItemDao.flush();

		return fileItem;
	}

	// -----------------------------------------------------------------

	String getOriginalFileName(String name) {
		return name.substring(0, name.lastIndexOf(" ("+ DUPLICATE_SUFFIX_STRING));
	}

	String getDuplicateNameWithSuffix(String fileName, String suffix) {
		return new StringBuilder(fileName).insert(fileName.lastIndexOf('.'), suffix).toString();
	}

	private String getNextAvailableSuffix(List<String> fileNames) {
		int suffixNumber = getNextAvailableSuffixNumber(fileNames);
		return String.format(DUPLICATE_SUFFIX_STRING_PATTERN, suffixNumber);
	}

	int getNextAvailableSuffixNumber(List<String> fileNames) {
		Set<String> fileNameSet = new HashSet<>(fileNames);
		if(fileNames.isEmpty()) {
			return 1;
		}

		String fileNameWithoutExtension = getOriginalFileName(fileNames.get(0));
		String extension = getFileExtension(fileNames.get(0));
		String fileNameFormat = fileNameWithoutExtension + DUPLICATE_SUFFIX_STRING_PATTERN + extension;
		int suffixNumber = 1;
		while(fileNameSet.contains(String.format(fileNameFormat, suffixNumber))) {
			suffixNumber++;
		}

		return suffixNumber;
	}

	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'));
	}

	private DirectoryItem getParent(Integer parentId) {
		if (parentId == null) {
			return null;
		}
		return directoryItemDao.find(parentId).orElse(null);
	}

}
