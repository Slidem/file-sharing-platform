package com.file.sharing.core.factory.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.factory.BasicItemInfoFactory;
import com.file.sharing.core.objects.AbstractBasicItemInfo;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.directory.BasicDirectoryInfo;
import com.file.sharing.core.objects.directory.BasicDirectoryInfoImpl;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.BasicFileInfoImpl;
import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Dec 25, 2017
 * 
 */
@Component
public class BasicItemInfoFactoryImpl implements BasicItemInfoFactory {

	@Override
	public BasicItemInfo fromItemEntity(Item item) {
		//@formatter:off
		return isDirectory(item) ? 
			   fromDirectoryItemEntity((DirectoryItem) item) : 
			   fromFileItemEntity((FileItem) item);
		//@formatter:on
	}

	@Override
	public BasicFileInfo fromFileItemEntity(FileItem fileItem) {
		BasicFileInfoImpl.BasicFileInfoBuilder builder = BasicFileInfoImpl.newBuilder();
		addCommonInfo(builder, fileItem);
		return builder.withCategory(fileItem.getCategory().getCategory()).build();
	}

	@Override
	public BasicDirectoryInfo fromDirectoryItemEntity(DirectoryItem directoryItem) {
		BasicDirectoryInfoImpl.BasicDirectoryInfoBuilder builder = BasicDirectoryInfoImpl.newBuilder();
		addCommonInfo(builder, directoryItem);
		return builder.build();
	}
	
	// --------------------------------------------------------------------------------------

	private void addCommonInfo(AbstractBasicItemInfo.Builder<? extends AbstractBasicItemInfo, ?> builder, Item item) {
		//@formatter:off
		builder.withId(item.getId())
		       .withItemType(item.getItemType())
		       .withName(item.getName());
		//@formatter:on
	}
	
	private boolean isDirectory(Item item) {
		return ItemType.DIRECTORY == item.getItemType();
	}
}
