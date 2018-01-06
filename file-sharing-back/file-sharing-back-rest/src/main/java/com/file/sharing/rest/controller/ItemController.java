package com.file.sharing.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.core.objects.Context;
import com.file.sharing.core.service.ItemActionService;
import com.file.sharing.core.service.ItemService;
import com.file.sharing.rest.dto.AbstractBasicItemInfoDTO;
import com.file.sharing.rest.factory.ItemActionDtoFactory;
import com.file.sharing.rest.factory.ItemBasicInfoDtoFactory;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Autowired
	protected ItemActionService itemActionService;

	@Autowired
	protected Context context;

	@Autowired
	protected ItemActionDtoFactory itemActionDtoFactory;
	
	@Autowired
	protected ItemBasicInfoDtoFactory itemBasicInfoDtoFactory;
	
	@Autowired
	protected ItemService itemService;
	
	@GetMapping
	public List<AbstractBasicItemInfoDTO> getItemInfoByParentId(@RequestParam(required = false) Integer parentId) {
		List<AbstractBasicItemInfoDTO> result = itemService.getBasicItemInfoByParentId(parentId)
														.stream()
														.map(itemBasicInfoDtoFactory::toDto)
														.collect(Collectors.toList());
		return result;
	}
}
