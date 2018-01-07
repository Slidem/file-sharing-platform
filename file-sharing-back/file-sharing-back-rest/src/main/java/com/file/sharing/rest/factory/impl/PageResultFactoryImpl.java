package com.file.sharing.rest.factory.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.rest.dto.PageResultDTO;
import com.file.sharing.rest.dto.file.BasicFileInfoDTO;
import com.file.sharing.rest.factory.ItemBasicInfoDtoFactory;
import com.file.sharing.rest.factory.PageResultFactory;

@Component
public class PageResultFactoryImpl implements PageResultFactory {
	
	private ItemBasicInfoDtoFactory itemBasicInfoDtoFactory;
	
	@Autowired
	public PageResultFactoryImpl(ItemBasicInfoDtoFactory itemBasicInfoDtoFactory) {
		this.itemBasicInfoDtoFactory = itemBasicInfoDtoFactory;
	}

	@Override
	public PageResultDTO<BasicFileInfoDTO> toBasicFileInfoResultDto(PageResult<BasicFileInfo> result) {
		List<BasicFileInfoDTO> convertedResult = result.getResult().stream().map(itemBasicInfoDtoFactory::toDto).collect(Collectors.toList()); 
		return new PageResultDTO.Builder<BasicFileInfoDTO>()
				 .withPageSize(result.getPageSize())
				 .withTotalPageCount(result.getTotalPageCount())
				 .withResult(convertedResult)
				 .build();
	}

}
