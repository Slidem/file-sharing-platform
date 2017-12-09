package com.file.sharing.core.business;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.objects.file.ItemActionType;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public interface ItemsActionBusiness {

	/**
	 * @param action
	 * @return the saved directory item entity
	 */
	public DirectoryItem saveDirectoryItem(CreateDirectoryAction action);

	/**
	 * @param fileItem
	 */
	public void saveFileItemAction(Integer itemId, ItemActionType actionType);

	/**
	 * @param itemId
	 */
	public void deleteItem(Integer itemId);

	/**
	 * @param itemId
	 * @param itemName
	 */
	public void renameItem(Integer itemId, String itemName);

	/**
	 * @param itemId
	 * @param newParentId
	 */
	public void moveItem(Integer itemId, Integer newParentId);

	/**
	 * @param uploadAction
	 */
	public FileItem saveFileItem(UploadFileAction uploadAction);

}
