package com.file.sharing.core.service;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.actions.file.RenameFileAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.objects.file.FileData;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public interface ItemActionService {

	/**
	 * Creates a directory under the user's root directory.
	 * 
	 * @param directoryName Name of the directory. Cannot be null or empty. 
	 * 
	 * @throws IllegalArgumentException if the directoryName is null or empty
	 */
	CreateDirectoryAction createDirectory(String directoryName);

	/**
	 * @param parentId Id of the parent directory.
	 * @param directoryName Name of the directory. Cannot be null or empty. 
	 * 
	 * @throws IllegalArgumentException if the directoryName is null or empty
	 * @throws ItemNotFoundException if the parentId is an invalid directory id 
	 * 	
	 **/
	CreateDirectoryAction createDirectory(Integer parentId, String directoryName);

	/**
	 * @param directoryId
	 * 
	 *
	 * @throws IllegalArgumentException if the directoryId is null
	 * @throws ItemNotFoundException directory id is an invalid one
	 */
	DeleteDirectoryAction deleteDirectory(Integer directoryId);

	/**
	 * @param directoryId
	 * @param newName
	 * 
	 * @throws IllegalArgumentException if the directoryId is null
	 * @throws IllegalArgumentException if the newName is null, empty, or is the same as the previous name of the directory
	 * @throws ItemNotFoundException directory id is an invalid one
	 */
	RenameDirectoryAction renameDirectory(Integer directoryId, String newName);

	/**
	 * @param newParentId
	 *            -> if parentId is null, the directory will be moved under the
	 *            user's root
	 * @param directoryId
	 * @param userId
	 * 
	 * @throws IllegalArgumentException if the directoryId is null
	 * @throws ItemNotFoundException directory id, or newParentId is an invalid one
	 */
	MoveDirectoryAction moveDirectory(Integer directoryId, Integer newParentId);

	/**
	 * @param parentId
	 *            -> if parentId is null, the file will be uploaded under the
	 *            user's root
	 * @param fileData
	 */
	UploadFileAction uploadFile(Integer parentId, FileData fileData);

	/**
	 * @param fileId
	 * 
	 * @throws IllegalArgumentException if the fileId is null
	 * @throws ItemNotFoundException fileId is an invalid one
	 */
	DeleteFileAction deleteFile(Integer fileId);

	/**
	 * @param fileId
	 * @param newName
	 * 
	 * @throws IllegalArgumentException if the fileId is null
	 * @throws IllegalArgumentException if the newName is null, empty, or is the same as the previous name of the file
	 * @throws ItemNotFoundException file id is an invalid one
	 */
	RenameFileAction renameFile(Integer fileId, String newName);

	/**
	 * @param fileId
	 * @param newParentId
	 */
	MoveFileAction moveFile(Integer fileId, Integer newParentId);
}
