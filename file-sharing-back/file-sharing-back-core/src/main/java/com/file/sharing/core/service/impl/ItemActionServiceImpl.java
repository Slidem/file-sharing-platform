package com.file.sharing.core.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.actions.directory.MoveDirectoryAction;
import com.file.sharing.core.actions.directory.RenameDirectoryAction;
import com.file.sharing.core.actions.file.DeleteFileAction;
import com.file.sharing.core.actions.file.MoveFileAction;
import com.file.sharing.core.actions.file.RenameFileAction;
import com.file.sharing.core.actions.file.UploadFileAction;
import com.file.sharing.core.exception.FileSharingException;
import com.file.sharing.core.handler.action.ItemActionHandlerRegistry;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileData;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.service.ItemActionService;
import com.file.sharing.core.service.ItemService;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Service
public class ItemActionServiceImpl implements ItemActionService {

	private ItemActionHandlerRegistry eventHandlerRegistry;

	private ItemService itemService;

	private Context context;

	@Autowired
	public ItemActionServiceImpl(ItemActionHandlerRegistry eventHandlerRegistry, ItemService itemService,
			Context context) {
		this.eventHandlerRegistry = eventHandlerRegistry;
		this.itemService = itemService;
		this.context = context;
	}

	@Override
	public void createDirectory(String directoryName) {
		createDirectory(null, directoryName);
	}

	@Override
	public void createDirectory(Integer parentId, String directoryName) {

		if (directoryName == null || directoryName.isEmpty()) {
			throw new IllegalArgumentException("Directory name cannot be null or empty");
		}

		String directoryPath = getPath(parentId);

		CreateDirectoryAction createDirEvent = new CreateDirectoryAction.CreateDirectoryActionBuilder()
				.withParentId(parentId)
				.withItemName(directoryName)
				.withPath(directoryPath)
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(CreateDirectoryAction.class).handle(createDirEvent);
	}

	@Override
	public void deleteDirectory(Integer directoryId) {
		checkItemId(directoryId);

		DirectoryDetails directoryDetails = getDirectoryDetails(directoryId);

		DeleteDirectoryAction deleteDirEvent = new DeleteDirectoryAction.DeleteDirectoryActionBuilder()
				.withItemId(directoryDetails.getId())
				.withItemName(directoryDetails.getName())
				.withPath(directoryDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(DeleteDirectoryAction.class).handle(deleteDirEvent);
	}

	@Override
	public void renameDirectory(Integer directoryId, String newName) {

		if (directoryId == null || StringUtils.isEmpty(newName)) {
			throw new IllegalArgumentException("Directory id or new directory name cannot be null or empty");
		}

		DirectoryDetails directoryDetails = getDirectoryDetails(directoryId);

		if (directoryDetails.getName().equals(newName)) {
			throw new IllegalArgumentException("New directory name cannot be the same as the current one");
		}

		RenameDirectoryAction renameDirAction = new RenameDirectoryAction.RenameDirectoryActionBuilder()
				.withItemId(directoryDetails.getId())
				.withItemName(directoryDetails.getName())
				.withNewItemName(newName)
				.withPath(directoryDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();
		eventHandlerRegistry.getHandler(RenameDirectoryAction.class).handle(renameDirAction);
	}

	@Override
	public void moveDirectory(Integer newParentId, Integer directoryId) {
		checkItemId(directoryId);

		DirectoryDetails dirDetails = getDirectoryDetails(directoryId);

		if (dirDetails.getParent() != null && dirDetails.getParent().equals(newParentId)) {
			throw new IllegalArgumentException("New parentId cannot be the same as the previous one");
		}

		String newDirectoryPath = getPath(newParentId);

		MoveDirectoryAction moveDirAction = new MoveDirectoryAction.MoveDirectoryActionBuilder()
				.withItemId(directoryId)
				.withItemName(dirDetails.getName())
				.withNewPath(newDirectoryPath)
				.withNewParentId(newParentId)
				.withPath(dirDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(MoveDirectoryAction.class).handle(moveDirAction);
	}

	@Override
	public void uploadFile(Integer parentId, FileData fileData) {
		if (fileData == null) {
			throw new IllegalArgumentException("file data cannot be nulll");
		}

		String directoryPath = getPath(parentId);

		UploadFileAction uploadFileAction = new UploadFileAction.UploadFileActionBuilder()
				.withUserId(context.getGetUserId()).withParentId(parentId).withItemName(fileData.getFileName())
				.withPath(directoryPath).withBytes(fileData.getBytes()).build();

		eventHandlerRegistry.getHandler(UploadFileAction.class).handle(uploadFileAction);
	}

	@Override
	public void deleteFile(Integer fileId) {
		checkItemId(fileId);

		FileDetails fileDetails = getFileDetails(fileId);

		DeleteFileAction deleteAction = new DeleteFileAction.DeleteFileActionBuilder()
				.withItemId(fileId)
				.withItemName(fileDetails.getName())
				.withPath(fileDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(DeleteFileAction.class).handle(deleteAction);
	}

	@Override
	public void renameFile(Integer fileId, String newName) {
		if (fileId == null || StringUtils.isEmpty(newName)) {
			throw new IllegalArgumentException("File id or new file name cannot be null or empty");
		}
		FileDetails fileDetails = getFileDetails(fileId);

		if (fileDetails.getName().equals(newName)) {
			throw new IllegalArgumentException("New file name cannot be the same as the current one");
		}

		RenameFileAction renameAction = new RenameFileAction.RenameFileActionBuider()
				.withItemId(fileId)
				.withItemName(fileDetails.getName())
				.withNewItemName(newName)
				.withPath(fileDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(RenameFileAction.class).handle(renameAction);
	}

	@Override
	public void moveFile(Integer fileId, Integer newParentId) {
		checkItemId(fileId);

		FileDetails fileDetails = getFileDetails(fileId);

		if (fileDetails.getParent() != null && fileDetails.getParent().equals(newParentId)) {
			throw new IllegalArgumentException("New parentId cannot be the same as the previous one");
		}

		String newDirectoryPath = getPath(newParentId);

		MoveFileAction moveFileAction = new MoveFileAction.MoveFileActionBuilder()
				.withItemId(fileId)
				.withItemName(fileDetails.getName())
				.withNewPath(newDirectoryPath)
				.withNewParentId(newParentId)
				.withPath(fileDetails.getPath())
				.withUserId(context.getGetUserId())
				.build();

		eventHandlerRegistry.getHandler(MoveFileAction.class).handle(moveFileAction);
	}

	@Override
	public File retrieveFile(Integer fileId) {
		checkItemId(fileId);
		
		FileDetails fileDetails = getFileDetails(fileId);

		return new File(fileDetails.getPath() + File.separator + fileDetails.getName());
	}

	// ----------------------------------------------------------------------------------

	private void checkItemId(Integer fileId) {
		if (fileId == null) {
			throw new IllegalArgumentException("Item id cannot be null.");
		}
	}

	private FileDetails getFileDetails(Integer fileId) {
		try {
			return itemService.getFileDetails(fileId);
		} catch (IOException e) {
			throw new FileSharingException(
					"Could not retrieve item details for item id: " + fileId + " userId: " + context.getGetUserId(), e);
		}
	}

	private DirectoryDetails getDirectoryDetails(Integer directoryId) {
		try {
			return itemService.getDirectoryDetails(directoryId);
		} catch (IOException e) {
			throw new FileSharingException("Could not retrieve item details for item id: " + directoryId + " userId: "
					+ context.getGetUserId(), e);
		}
	}

	private String getPath(Integer itemId) {
		return itemId == null ? context.getUserStorageInfo().getLocation() : itemService.getItemFullPath(itemId);
	}
}
