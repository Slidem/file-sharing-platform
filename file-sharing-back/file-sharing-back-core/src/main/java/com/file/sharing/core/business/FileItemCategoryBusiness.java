package com.file.sharing.core.business;

import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 * 
 */
public interface FileItemCategoryBusiness {

	/**
	 * @param itemName
	 * @return
	 */
	FileItemCategory getFileItemCategoryFromItemName(String itemName);

	/**
	 * @param category
	 * @param extension
	 * @return
	 */
	FileItemCategory getFileItemCategory(FileCategories category, String extension);
	
}
