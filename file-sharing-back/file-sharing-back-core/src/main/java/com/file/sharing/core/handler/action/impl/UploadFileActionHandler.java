package com.file.sharing.core.handler.action.impl;

import java.io.File;
import java.io.IOException;

import com.file.sharing.core.business.ItemsActionBusiness;
import com.file.sharing.core.entity.FileItem;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

import static com.file.sharing.core.objects.file.ItemActionType.UPLOAD_FILE;

/**
 * @author Alexandru Mihai
 * @created Nov 30, 2017
 * 
 */
@Component
public class UploadFileActionHandler extends AbstractItemActionHandler<UploadFileAction> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	ItemsActionBusiness itemsActionBusiness;

	@Autowired
	public UploadFileActionHandler(ApplicationEventPublisher applicationEventPublisher, ItemsActionBusiness itemsActionBusiness) {
		super(applicationEventPublisher);
		this.itemsActionBusiness = itemsActionBusiness;
	}

	@Override
	public Class<UploadFileAction> getActionClass() {
		return UploadFileAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(UploadFileAction action) {
		FileItem fileItem = itemsActionBusiness.saveFileItem(action);
		itemsActionBusiness.saveFileItemAction(fileItem.getId(), UPLOAD_FILE);
		return ItemActionStatus.SUCCESS;
	}

}