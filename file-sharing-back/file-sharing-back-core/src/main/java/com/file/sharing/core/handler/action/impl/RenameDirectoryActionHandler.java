package com.file.sharing.core.handler.action.impl;

import static com.file.sharing.core.objects.file.ItemActionStatus.FAILURE;
import static com.file.sharing.core.objects.file.ItemActionStatus.SUCCESS;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 26, 2017
 */
@Component
public class RenameDirectoryActionHandler extends AbstractItemActionHandler<RenameDirectoryAction> {

	@Autowired
	public RenameDirectoryActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<RenameDirectoryAction> getActionClass() {
		return RenameDirectoryAction.class;
	}

	@Override
	protected ItemActionStatus handleAction(RenameDirectoryAction action) {

		String path = action.getPath();
		String name = action.getItemName();
		String newName = action.getNewItemName();
		
		boolean succeded = new File(path, name).renameTo(new File(path, newName));
		return succeded ? SUCCESS : FAILURE;

	}

}
