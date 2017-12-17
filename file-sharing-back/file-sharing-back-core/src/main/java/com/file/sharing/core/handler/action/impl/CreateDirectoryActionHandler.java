package com.file.sharing.core.handler.action.impl;

import static com.file.sharing.core.objects.file.ItemActionStatus.SUCCESS;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;
import com.file.sharing.core.service.ItemService;
import com.file.sharing.core.service.StorageService;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Component
public class CreateDirectoryActionHandler extends AbstractItemActionHandler<CreateDirectoryAction> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public CreateDirectoryActionHandler(ApplicationEventPublisher applicationEventPublisher,
			StorageService storageService, ItemService itemDetailsService) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<CreateDirectoryAction> getActionClass() {
		return CreateDirectoryAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(CreateDirectoryAction itemAction) {

		try {
			Path fullPath = getFullPath(itemAction.getPath(), itemAction.getItemName());
			Files.createDirectory(fullPath);
		} catch (FileAlreadyExistsException e) {
			logger.info(e.getMessage(), e);
			return ItemActionStatus.FILE_ALREADY_EXISTS;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return ItemActionStatus.FAILURE;
		}

		return SUCCESS;
	}

	private Path getFullPath(String root, String directoryName) {
		String fullPath = root + File.separator + directoryName;
		return Paths.get(fullPath);
	}

}
