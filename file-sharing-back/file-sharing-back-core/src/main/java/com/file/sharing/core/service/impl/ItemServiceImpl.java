package com.file.sharing.core.service.impl;

import static java.nio.file.Files.readAttributes;
import static java.util.Optional.ofNullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.core.business.ItemInfoBusiness;
import com.file.sharing.core.dao.DirectoryItemDao;
import com.file.sharing.core.dao.FileItemDao;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.FileSharingException;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.ItemActionInfo;
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
public class ItemServiceImpl implements ItemService {

	private final ItemDao itemDao;

	private final DirectoryItemDao directoryItemDao;

	private final FileItemDao fileItemDao;

	private final Context context;

	private final ItemInfoBusiness itemInfoBusiness;

	@Autowired{
	public ItemsServiceImpl(ItemDao itemDao, DirectoryItemDao directoryItemDao, FileItemDao fileItemDao,
			Context context, ItemInfoBusiness itemInfoBusiness) {
		this.itemDao = itemDao;
		this.directoryItemDao = directoryItemDao;
		this.fileItemDao = fileItemDao;
		this.context = context;
		this.itemInfoBusiness = itemInfoBusiness;
	}

	@Override
	public String getItemFullPath(int itemId) {
		Optional<Item> item = itemDao.find(itemId);

		String fullPath = item.map(this::getPathAsString).orElse(null);

		if (fullPath == null) {
			throwItemNotFound(itemId);
		}

		return fullPath;
	}

	// TODO: Create factories and move business in business component
	@Override
	public DirectoryDetails getDirectoryDetails(int directoryId) throws IOException {
		DirectoryItem directoryItem = directoryItemDao.find(directoryId).orElse(null);

		if (directoryItem == null) {
			throwItemNotFound(directoryId);
		}

		BasicFileAttributes attr = readAttributes(getPath(directoryItem), BasicFileAttributes.class);

				.withId(directoryId)				.withLastModified(attr.lastModifiedTime().toInstant())
				.withName(directoryItem.getName())
				.withParent(getParentId(directoryItem))
				.withPath(directoryItem.getPath())
				.withSize(attr.size())
				.withCreationTime(attr.creationTime().toInstant())
				.build();

	}

	@Override
	public FileDetails getFileDetails(int fileId) throws IOException {
		FileItem fileItem = fileItemDao.find(fileId).orElse(null);

		if (fileItem == null) {
			throwItemNotFound(fileId);
		}

		BasicFileAttributes attr = readAttributes(getPath(fileItem), BasicFileAttributes.class);


		return new FileDetails.FileDetailsBuilder().withId(fileItem.getId())
				.withCategory(fileItem.getCategory().getCategory())
				.withExtension(fileItem.getCategory().getExtension())
				.withName(fileItem.getName())
				.withParent(getParentId(fileItem))
				.withPath(fileItem.getPath())
				.withUploadTime(fileItem.getUploadTime().toInstant())
				.withSize(attr.size())
				.withLastModified(attr.lastModifiedTime().toInstant())
				.build();
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

	private Integer getParentId(Item item) {
		return ofNullable(item.getParent()).map(Item::getId).orElse(null);
	}

	private String getPathAsString(Item item) {
		return item.getPath() + File.separator + item.getName();
	}

	private Path getPath(Item item) {
		return Paths.get(getPathAsString(item));
	}

	@Override
	public List<Item> getItemsByParentId(int parentId) {
		List<Item> result = itemDao.getItemsByParentId(parentId);
		return result;
	}

	private static void throwItemNotFound(Integer itemId) {
		throw new ItemNotFoundException("Item not found for id: " + itemId);
