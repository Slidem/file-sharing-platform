package com.file.sharing.rest.factory.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.rest.dto.ItemActionInfoDTO;
import com.file.sharing.rest.factory.ItemActionInfoDtoFactory;

@Component
public class ItemActionInfoDtoFactoryImpl implements ItemActionInfoDtoFactory {

	@Override
	public ItemActionInfoDTO toDto(ItemActionInfo itemActionInfo) {
		Objects.requireNonNull(itemActionInfo);
		return new ItemActionInfoDTO.Builder()
				.withActionTime(itemActionInfo.getActionTime())
				.withBasicItemInfo(itemActionInfo.getBasicItemInfo().get())
				.withItemActionType(itemActionInfo.getActionType())
				.build();
	}
}
