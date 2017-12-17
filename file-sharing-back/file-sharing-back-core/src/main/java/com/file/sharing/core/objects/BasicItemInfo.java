package com.file.sharing.core.objects;

import com.file.sharing.core.objects.file.ItemType;

/**
 * Lightweight item information.
 * 
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 * 
 */
public interface BasicItemInfo {

	/**
	 * @return the id of the item
	 */
	Integer getId();

	/**
	 * @return the name of the item. If it's a file, returns the full name,
	 *         including the extension.
	 */
	String getName();

	/**
	 * @return the type of the item: file / folder
	 */
	ItemType getItemType();

}
