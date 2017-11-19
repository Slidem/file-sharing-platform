package com.file.sharing.core.business;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.objects.file.FileActionType;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public interface ItemsActionBusiness {

	/**
	 * @param action
	 * @return the saved directory item entity
	 */
	public DirectoryItem saveDirectoryItem(CreateDirectoryAction action, User user);

	/**
	 * @param fileItem
	 */
	public void saveFileItemAction(Item item, FileActionType actionType);

}
