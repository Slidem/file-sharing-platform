package com.file.sharing.rest.factory.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.ItemDetailsVisitor;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.rest.dto.ItemDetailsDTO;
import com.file.sharing.rest.dto.directory.DirectoryDetailsDTO;
import com.file.sharing.rest.dto.directory.DirectoryDetailsDTO.DirectoryDetailsDTOBuilder;
import com.file.sharing.rest.dto.file.FileDetailsDTO;
import com.file.sharing.rest.dto.file.FileDetailsDTO.FileDetailsDTOBuilder;

/**
 * @author Alexandru Mihai
 * @created Jan 6, 2018
 * 
 */
@Component
@Scope(value = "prototype")
public class ItemDetailsDtoFactoryVisitor implements ItemDetailsVisitor {

	private ItemDetailsDTO itemDetailsDTO;

	@Override
	public void visit(FileDetails fileDetails) {
		FileDetailsDTO.FileDetailsDTOBuilder builder = new FileDetailsDTOBuilder();
		addCommonProperties(builder, fileDetails);
		itemDetailsDTO = builder.withCategory(fileDetails.getCategory())
				                .withExtension(fileDetails.getExtension())
				                .withUploadTime(fileDetails.getUploadTime())
				                .build();
	}

	@Override
	public void visit(DirectoryDetails directory) {
		DirectoryDetailsDTO.DirectoryDetailsDTOBuilder builder = new DirectoryDetailsDTOBuilder();
		addCommonProperties(builder, directory);
		itemDetailsDTO = builder.withCreationTime(directory.getCreationTime()).build();
	}

	private void addCommonProperties(ItemDetailsDTO.ItemBuilder<? extends ItemDetailsDTO, ?> builder, ItemDetails itemDetails) {
		//@formatter:off
		builder.withId(itemDetails.getId())
		       .withLastModified(itemDetails.getLastModified())
		       .withName(itemDetails.getName())
		       .withParent(itemDetails.getParent())
		       .withPath(itemDetails.getPath())
		       .withSize(itemDetails.getSize());
		//@formatter:on
	}

	public ItemDetailsDTO getItemDetailsDTO() {
		return itemDetailsDTO;
	}

}
