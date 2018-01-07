package com.file.sharing.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.core.business.ItemInfoBusiness;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.FileSharingException;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.OrderValue;
import com.file.sharing.core.service.ItemService;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Service
@Transactional(readOnly = true)
public class ItemsServiceImpl implements ItemService {

	private final ItemDao itemDao;

	private final Context context;

	private final ItemInfoBusiness itemInfoBusiness;

	@Autowired
	public ItemsServiceImpl(ItemDao itemDao, Context context, ItemInfoBusiness itemInfoBusiness) {
		this.itemDao = itemDao;
		this.context = context;
		this.itemInfoBusiness = itemInfoBusiness;
	}

	@Override
	public String getItemFullPath(int itemId) {
		Optional<Item> item = itemDao.find(itemId);

		String fullPath = item.map(this::getPath).orElse(null);

		if (fullPath == null) {
			throwItemNotFound(itemId);
		}

		return fullPath;
	}

	@Override
	public ItemDetails getItemDetails(int itemId) throws IOException {
		return itemInfoBusiness.getItemDetails(itemId);
	}

	@Override
	public DirectoryDetails getDirectoryDetails(int directoryId) throws IOException {
		return itemInfoBusiness.getDirectoryDetails(directoryId);
	}

	@Override
	public FileDetails getFileDetails(int fileId) throws IOException {
		return itemInfoBusiness.getFileDetails(fileId);
	}

	@Override
	public File retrieveFile(Integer fileId) {
		if (fileId == null) {
			throw new IllegalArgumentException("Item id cannot be null.");
		}

		FileDetails fileDetails;

		try {
			fileDetails = getFileDetails(fileId);
		} catch (IOException e) {
			throw new FileSharingException("Could not retrieve item details for item id: " + fileId + " userId: " + context.getGetUserId(), e);
		}

		return new File(fileDetails.getPath() + File.separator + fileDetails.getName());
	}

	@Override
	public PageResult<BasicFileInfo> searchFiles(ItemSearch itemSearch) {
		return itemInfoBusiness.getFilesInfo(itemSearch);
	}

	public List<ItemActionInfo> getItemActionsInfo(Integer itemId) {
		return getItemActionsInfo(itemId, OrderValue.DESC);
	}

	public List<ItemActionInfo> getItemActionsInfo(Integer itemId, OrderValue orderValue) {
		return itemInfoBusiness.getItemActionsInfo(Objects.requireNonNull(itemId), Objects.requireNonNull(orderValue));
	}

	// -----------------------------------------------------------------------------

	private String getPath(Item item) {
		return item.getPath() + File.separator + item.getName();
	}

	private static void throwItemNotFound(Integer itemId) {
		throw new ItemNotFoundException("Item not found for id: " + itemId);
	}



}
