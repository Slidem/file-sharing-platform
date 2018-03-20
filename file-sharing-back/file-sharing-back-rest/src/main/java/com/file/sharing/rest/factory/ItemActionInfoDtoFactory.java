package com.file.sharing.rest.factory;

import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.rest.dto.ItemActionInfoDTO;

/**
 * @author Andrei Aioanei
 * @created Mar 3, 2018
 *
 */
public interface ItemActionInfoDtoFactory {
	
	/**
	 * 
	 * @param itemActionInfo
	 * @return
	 */
	ItemActionInfoDTO toDto(ItemActionInfo itemActionInfo);
}
