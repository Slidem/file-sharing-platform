package com.file.sharing.core.handler.action.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 30, 2017
 * 
 */
@Component
public class UploadFileActionHandler extends AbstractItemActionHandler<UploadFileAction> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UploadFileActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<UploadFileAction> getActionClass() {
		return UploadFileAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(UploadFileAction action) {
		File file = new File(action.getPath(), action.getItemName());
		if (file.exists()) {
			try {
				FileUtils.writeByteArrayToFile(file, action.getBytes());
				return ItemActionStatus.SUCCESS;
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
				return ItemActionStatus.FAILURE;
			}
		}
		return ItemActionStatus.FILE_ALREADY_EXISTS;
	}

}
