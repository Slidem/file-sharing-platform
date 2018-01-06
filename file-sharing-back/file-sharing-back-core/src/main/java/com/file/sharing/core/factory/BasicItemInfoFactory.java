package com.file.sharing.core.factory;

import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.directory.BasicDirectoryInfo;
import com.file.sharing.core.objects.file.BasicFileInfo;

/**
 * @author Alexandru Mihai
 * @created Dec 25, 2017
 * 
 */
public interface BasicItemInfoFactory {

	/**
	 * @param item
	 * @return
	 */
	BasicItemInfo fromItemEntity(Item item);

	/**
	 * @param fileItem
	 * @return
	 */
	BasicFileInfo fromFileItemEntity(FileItem fileItem);

	/**
	 * @param directoryItem
	 * @return
	 */
	BasicDirectoryInfo fromDirectoryItemEntity(DirectoryItem directoryItem);
}
