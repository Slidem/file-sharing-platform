package com.file.sharing.core.dao;

import java.util.List;
import java.util.Optional;

import com.file.sharing.core.entity.ItemActionEntity;
import com.file.sharing.core.search.OrderValue;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public interface ItemActionDao extends AbstractDao<ItemActionEntity> {

	/**
	 * @param itemId
	 * @return
	 */
	Optional<ItemActionEntity> findLastItemAction(Integer itemId);

	/**
	 * @param itemId
	 * @return
	 */
	List<ItemActionEntity> findActionsBasedOnItemId(Integer itemId, OrderValue actionTimeOrder);

}
