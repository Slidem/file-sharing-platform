package com.file.sharing.core.service.impl;

import static java.nio.file.Files.readAttributes;
import static java.util.Optional.ofNullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.core.dao.DirectoryItemDao;
import com.file.sharing.core.dao.FileItemDao;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.service.ItemService;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Service
@Transactional(readOnly = true)
public class ItemsServiceImpl implements ItemService {

	private final ItemDao itemDao;

	private final DirectoryItemDao directoryItemDao;

	private final FileItemDao fileItemDao;

	@Autowired
	public ItemsServiceImpl(ItemDao itemDao, DirectoryItemDao directoryItemDao, FileItemDao fileItemDao) {
		this.itemDao = itemDao;
		this.directoryItemDao = directoryItemDao;
		this.fileItemDao = fileItemDao;
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

	@Override
	public DirectoryDetails getDirectoryDetails(int directoryId) throws IOException {
		DirectoryItem directoryItem = directoryItemDao.find(directoryId).orElse(null);

		if (directoryItem == null) {
			throwItemNotFound(directoryId);
		}

		BasicFileAttributes attr = readAttributes(getPath(directoryItem), BasicFileAttributes.class);

		return new DirectoryDetails.DirectoryBuilder()
				.withId(directoryId)
				.withLastModified(attr.lastModifiedTime().toInstant())
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
		
		return new FileDetails.FileDetailsBuilder()
				.withId(fileItem.getId())
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

	private Integer getParentId(Item item) {
		return ofNullable(item.getParent()).map(Item::getId).orElse(null);
	}


	private String getPathAsString(Item item) {
		return item.getPath() + File.separator + item.getName();
	}

	private Path getPath(Item item) {
		return Paths.get(getPathAsString(item));
	}


	private static void throwItemNotFound(Integer itemId) {
		throw new ItemNotFoundException("Item not found for id: " + itemId);
	}



}
