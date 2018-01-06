package com.file.sharing.core.search;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Dec 23, 2017
 * 
 */
public interface ItemSearch {

	PageSearch getPageSearch();

	Map<ItemSearchOrder, OrderValue> getOrderCriteriaMap();

	Optional<String> getItemName();

	Optional<List<FileCategories>> getCategories();

	Optional<String> getExtension();

}
