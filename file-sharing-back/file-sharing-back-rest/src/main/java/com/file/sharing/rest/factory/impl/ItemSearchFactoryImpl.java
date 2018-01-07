package com.file.sharing.rest.factory.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.ItemSearchOrder;
import com.file.sharing.core.search.OrderValue;
import com.file.sharing.core.search.PageSearch;
import com.file.sharing.core.search.impl.ItemSearchImpl;
import com.file.sharing.core.search.impl.PageSearchImpl;
import com.file.sharing.rest.dto.ItemSearchDTO;
import com.file.sharing.rest.factory.ItemSearchFactory;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
@Component
public class ItemSearchFactoryImpl implements ItemSearchFactory {

	@Override
	public ItemSearch fromDto(ItemSearchDTO itemSearchDto) {
		
		PageSearch pageSearch = PageSearchImpl.of(itemSearchDto.getPageNumber(), itemSearchDto.getPageSize());
		ItemSearchOrder orderCriteria = getOrderCriteria(itemSearchDto);
		OrderValue orderValue = getOrderValue(itemSearchDto);
		
		//@formatter:off
		ItemSearchImpl.Builder builder = new ItemSearchImpl.Builder()
				.withCategories(toEnumList(itemSearchDto.getCategories()))
				.withExtension(itemSearchDto.getExtension())
				.withItemName(itemSearchDto.getItemName())
				.withPageSearch(pageSearch);
		//@formatter:on
		
		if(orderCriteria != null) {
			builder.withOrderCriteria(orderCriteria, orderValue);
		}
		
		return builder.build();
	}

	private OrderValue getOrderValue(ItemSearchDTO itemSearchDto) {
		return Optional.ofNullable(itemSearchDto.getOrderValue()).map(OrderValue::valueOf).orElse(null);
	}

	private ItemSearchOrder getOrderCriteria(ItemSearchDTO itemSearchDto) {
		return Optional.ofNullable(itemSearchDto.getOrderCriteria()).map(ItemSearchOrder::valueOf).orElse(null);
	}


	private List<FileCategories> toEnumList(Set<String> categories) {
		if (categories == null) {
			return null;
		}

		return categories.stream().map(FileCategories::valueOf).collect(Collectors.toList());
	}

}
