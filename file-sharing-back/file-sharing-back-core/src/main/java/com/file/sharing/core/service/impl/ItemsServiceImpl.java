package com.file.sharing.core.service.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;

import com.file.sharing.core.events.ItemTransactionEvent;
import com.file.sharing.core.service.ItemDetailsService;
import com.file.sharing.core.service.ItemsService;
import com.file.sharing.core.service.StorageService;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public class ItemsServiceImpl implements ItemsService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private StorageService storageService;

	private ItemDetailsService itemDetailsService;

	private ApplicationEventPublisher eventPublisher;

	@Override
	public void createDirectory(String directoryName, Integer userId) {
		createDir(null, directoryName, userId);
	}

	@Override
	public void createDirectory(Integer parentId, String directoryName, Integer userId) {
		Objects.requireNonNull(parentId);
		createDir(parentId, directoryName, userId);
	}

	@Override
	public void deleteDirectory(Integer directoryId, Integer userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameDirectory(Integer directoryId, String newName) {
		// TODO Auto-generated method stub

	}

	private void createDir(Integer parentId, String directoryName, Integer userId) {
		Objects.requireNonNull(directoryName);
		Objects.requireNonNull(userId);
		if (StringUtils.isEmpty(directoryName)) {
			throw new IllegalArgumentException("Directory name cannot be empty");
		}
		Path root = parentId == null ? storageService.getStoragePath(userId) : itemDetailsService.getItemPath(parentId);
		Path fullPath = fullDirectoryPath(root, directoryName);

		ItemTransactionEvent itemTransactionEvent = null;

		try {
			Files.createDirectory(fullPath);
		} catch (FileAlreadyExistsException e) {
			logger.info(e.getMessage(), e);
			eventPublisher.publishEvent(itemTransactionEvent);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			eventPublisher.publishEvent(itemTransactionEvent);
		}
	}

	private Path fullDirectoryPath(Path root, String directoryName) {
		String fullPath = root.toString() + directoryName;
		return Paths.get(URI.create(fullPath));
	}

}
