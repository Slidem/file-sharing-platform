package com.file.sharing.core.service;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.actions.file.RenameFileAction;
import com.file.sharing.core.actions.file.UploadFileAction;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public interface ItemActionEventService {

	/**
	 * @param action
	 */
	public void directoryCreated(CreateDirectoryAction action);

	/**
	 * @param action
	 */
	public void directoryDeleted(DeleteDirectoryAction action);

	/**
	 * @param action
	 */
	public void directoryRanamed(RenameDirectoryAction action);

	/**
	 * @param action
	 */
	public void directoryMoved(MoveDirectoryAction action);

	/**
	 * @param action
	 */
	public void fileUploaded(UploadFileAction action);

	/**
	 * @param action
	 */
	public void fileDeleted(DeleteFileAction action);

	/**
	 * @param action
	 */
	public void fileRenamed(RenameFileAction action);

	/**
	 * @param action
	 */
	public void fileMoved(MoveFileAction action);

}
