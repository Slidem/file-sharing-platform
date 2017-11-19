package com.file.sharing.core.service;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public interface ItemsActionEventService {

	/**
	 * @param action
	 */
	public void directoryCreated(CreateDirectoryAction action);

	/**
	 * 
	 */
	public void directoryDeleted(DeleteDirectoryAction action);

}
