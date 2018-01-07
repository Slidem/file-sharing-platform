package com.file.sharing.rest.factory;

import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.rest.dto.ItemSearchDTO;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
public interface ItemSearchFactory {

	ItemSearch fromDto(ItemSearchDTO itemSearchDto);

}
