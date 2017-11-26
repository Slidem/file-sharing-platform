package com.file.sharing.core.handler.action.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 26, 2017
 */
@Component
public class MoveDirectoryActionHandler extends AbstractItemActionHandler<MoveDirectoryAction> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public MoveDirectoryActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<MoveDirectoryAction> getActionClass() {
		return MoveDirectoryAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(MoveDirectoryAction action) {

		File src = new File(action.getPath(), action.getItemName());
		File dest = new File(action.getNewPath(), action.getItemName());

		try {
			FileUtils.moveDirectory(src, dest);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			return ItemActionStatus.FAILURE;
		}

		return ItemActionStatus.SUCCESS;
	}

}
