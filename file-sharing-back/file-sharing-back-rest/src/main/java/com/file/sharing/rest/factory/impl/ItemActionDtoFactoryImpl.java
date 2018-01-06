package com.file.sharing.rest.factory.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.rest.factory.ItemActionDtoFactory;

/**
 * @author Alexandru Mihai
 * @created Dec 29, 2017
 * 
 */
@Component
public class ItemActionDtoFactoryImpl implements ItemActionDtoFactory {

	@Override
	public ItemActionDTO toDto(ItemAction itemAction) {
		//@formatter:off
		return new ItemActionDTO.Builder()
				.withItemName(itemAction.getItemName())
				.withPath(itemAction.getPath())
				.withActionTime(itemAction.getActionTime())
				.withUserId(itemAction.getUserId())
				.build();
		//@formatter:on
	}

}
