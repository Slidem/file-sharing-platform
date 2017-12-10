package com.file.sharing.core.handler.action.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Dec 10, 2017
 * 
 */
@Component
public class DeleteFileActionHandler extends AbstractItemActionHandler<DeleteFileAction> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DeleteFileActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<DeleteFileAction> getActionClass() {
		return DeleteFileAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(DeleteFileAction action) {
		Path filePath = Paths.get(action.getPath(), action.getItemName());
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			return ItemActionStatus.FAILURE;
		}
		return ItemActionStatus.SUCCESS;
	}

}
