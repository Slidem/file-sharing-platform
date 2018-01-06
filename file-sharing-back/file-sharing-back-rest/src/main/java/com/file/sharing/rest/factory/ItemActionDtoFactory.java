package com.file.sharing.rest.factory;

import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.core.actions.ItemAction;

/**
 * @author Alexandru Mihai
 * @created Dec 29, 2017
 * 
 */
public interface ItemActionDtoFactory {

	/**
	 * @param itemAction
	 * @return
	 */
	ItemActionDTO toDto(ItemAction itemAction);

}
