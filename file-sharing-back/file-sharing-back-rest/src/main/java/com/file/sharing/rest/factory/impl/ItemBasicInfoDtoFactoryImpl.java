package com.file.sharing.rest.factory.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.directory.BasicDirectoryInfo;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.ItemType;
import com.file.sharing.rest.dto.AbstractBasicItemInfoDTO;
import com.file.sharing.rest.dto.directory.BasicDirectoryInfoDTO;
import com.file.sharing.rest.dto.file.BasicFileInfoDTO;
import com.file.sharing.rest.factory.ItemBasicInfoDtoFactory;

/**
 * @author Alexandru Mihai
 * @created Dec 30, 2017
 * 
 */
@Component
public class ItemBasicInfoDtoFactoryImpl implements ItemBasicInfoDtoFactory {

	@Override
	public AbstractBasicItemInfoDTO toDto(BasicItemInfo itemInfo) {
		//@formatter:off
		return isDirectory(itemInfo) ? 
			   toDto((BasicDirectoryInfo) itemInfo) : 
			   toDto((BasicFileInfo) itemInfo);
		//@formatter:on	   
	}

	@Override
	public BasicFileInfoDTO toDto(BasicFileInfo basicFileInfo) {
		BasicFileInfoDTO.BasicFileInfoDtoBuilder builder = BasicFileInfoDTO.newBuilder();
		addCommonInfo(builder, basicFileInfo);
		return builder.withCategory(basicFileInfo.getCategory()).build();
	}

	@Override
	public BasicDirectoryInfoDTO toDto(BasicDirectoryInfo basicDirectoryInfo) {
		BasicDirectoryInfoDTO.BasicDirectoryInfoDtoBuilder builder = BasicDirectoryInfoDTO.newBuilder();
		addCommonInfo(builder, basicDirectoryInfo);
		return builder.build();
	}
	
	
	// --------------------------------------------------------------------------------------

	private void addCommonInfo(AbstractBasicItemInfoDTO.Builder<? extends AbstractBasicItemInfoDTO, ?> builder,
			BasicItemInfo itemInfo) {
		//@formatter:off
		builder.withId(itemInfo.getId())
		       .withItemType(itemInfo.getItemType())
		       .withName(itemInfo.getName());
		//@formatter:on
	}
	
	private boolean isDirectory(BasicItemInfo itemInfo) {
		return ItemType.DIRECTORY == itemInfo.getItemType();
	}

}
