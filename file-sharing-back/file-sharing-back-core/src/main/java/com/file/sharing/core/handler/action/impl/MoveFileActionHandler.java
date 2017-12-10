package com.file.sharing.core.handler.action.impl;

import static com.file.sharing.core.objects.file.ItemActionStatus.SUCCESS;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Dec 10, 2017
 * 
 */
@Component
public class MoveFileActionHandler extends AbstractItemActionHandler<MoveFileAction> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public MoveFileActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<MoveFileAction> getActionClass() {
		return MoveFileAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(MoveFileAction action) {

		File fileToMove = new File(action.getPath(), action.getItemName());
		File dest = new File(action.getNewPath());

		try {
			FileUtils.moveFileToDirectory(fileToMove, dest, false);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			return ItemActionStatus.FAILURE;
		}

		return SUCCESS;
	}

}
