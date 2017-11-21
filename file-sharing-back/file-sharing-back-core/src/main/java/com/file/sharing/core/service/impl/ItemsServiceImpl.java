package com.file.sharing.core.service.impl;

import static com.file.sharing.core.objects.Constants.ITEMS_EXECUTOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.file.sharing.core.actions.directory.CreateDirectoryAction;
import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.handler.action.ItemActionHandlerRegistry;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.service.ItemDetailsService;
import com.file.sharing.core.service.ItemsService;
import com.file.sharing.core.service.StorageService;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Service
public class ItemsServiceImpl implements ItemsService {

	private ItemActionHandlerRegistry eventHandlerRegistry;
	
	private StorageService storageService;
	
	private ItemDetailsService itemDetailsService;


	@Autowired
	public ItemsServiceImpl(ItemActionHandlerRegistry eventHandlerRegistry, StorageService storageService,
			ItemDetailsService itemDetailsService) {
		this.eventHandlerRegistry = eventHandlerRegistry;
		this.storageService = storageService;
		this.itemDetailsService = itemDetailsService;
	}

	@Override
	@Async(value = ITEMS_EXECUTOR)
	public void createDirectory(String directoryName, Integer userId) {
		createDirectory(null, directoryName, userId);
	}

	@Override
	@Async(value = ITEMS_EXECUTOR)
	public void createDirectory(Integer parentId, String directoryName, Integer userId) {

		if (directoryName == null || directoryName.isEmpty()) {
			throw new IllegalArgumentException("Directory name cannot be null or empty");
		}
		
		String directoryPath = parentId == null ? storageService.getStoragePath(userId)
				: itemDetailsService.getItemFullPath(parentId);

		CreateDirectoryAction createDirEvent = new CreateDirectoryAction.CreateDirectoryActionBuilder()
				.withItemName(directoryName)
				.withPath(directoryPath)
                .withUserId(userId)
                .build();

		eventHandlerRegistry.getHandler(CreateDirectoryAction.class).handle(createDirEvent);
	}

	@Override
	public void deleteDirectory(Integer directoryId, Integer userId) {
		if (directoryId == null) {
			throw new IllegalArgumentException("Directory id cannot be null");
		}
		
		DirectoryDetails directoryDetails = itemDetailsService.getDirectoryDetails(directoryId);
		
		DeleteDirectoryAction deleteDirEvent = new DeleteDirectoryAction.DeleteDirectoryActionBuilder()
				.withItemName(directoryDetails.getName())
				.withPath(directoryDetails.getPath())
				.withUserId(userId)
				.build();

		eventHandlerRegistry.getHandler(DeleteDirectoryAction.class).handle(deleteDirEvent);
	}

	@Override
	public void renameDirectory(Integer directoryId, Integer userId, String newName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDirectory(Integer parentId, Integer directoryId, Integer userId) {
		// TODO Auto-generated method stub

	}

}
