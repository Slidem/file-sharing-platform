package com.file.sharing.core.dao;

import java.util.List;

import com.file.sharing.core.entity.Item;

/**
 * @author Alexandru Mihai
 * @created Nov 12, 2017
 */
public interface ItemDao extends AbstractDao<Item> {

	/**
	 * 
	 * @param parentId
	 * @return a list of all the items under the same parent.
	 */
	public List<Item> getItemsByParentId(Integer parentId);
}
