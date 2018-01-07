package com.file.sharing.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.service.ItemService;
import com.file.sharing.rest.dto.ItemDetailsDTO;
import com.file.sharing.rest.factory.impl.ItemDetailsDtoFactoryVisitor;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ObjectFactory<ItemDetailsDtoFactoryVisitor> itemDetailsVisitorFactory;

	@GetMapping(value = "/{itemId}")
	public ItemDetailsDTO getItemDetails(@PathVariable(value = "itemId") Integer itemId) throws IOException {

		ItemDetails itemDetails = itemService.getItemDetails(itemId);

		ItemDetailsDtoFactoryVisitor visitor = itemDetailsVisitorFactory.getObject();

		itemDetails.accept(visitor);
		return visitor.getItemDetailsDTO();
	}

}
