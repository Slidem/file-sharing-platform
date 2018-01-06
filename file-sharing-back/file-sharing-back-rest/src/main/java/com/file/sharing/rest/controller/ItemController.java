package com.file.sharing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.core.objects.Context;
import com.file.sharing.core.service.ItemActionService;
import com.file.sharing.rest.factory.ItemActionDtoFactory;

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

}
