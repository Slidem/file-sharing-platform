package com.file.sharing.core.dao;

import java.util.Optional;

import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 * 
 */
public interface FileItemCategoryDao extends AbstractDao<FileItemCategory>{
	
	/**
	 * @param ext
	 * @param category
	 * @return
	 */
	Optional<FileItemCategory> findByCategoryAndExtension(String ext, FileCategories category);
	
}
