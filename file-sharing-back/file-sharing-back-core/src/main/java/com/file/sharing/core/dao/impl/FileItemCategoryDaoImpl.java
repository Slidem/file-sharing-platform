package com.file.sharing.core.dao.impl;

import java.util.Optional;

import javax.persistence.TypedQuery;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.FileItemCategoryDao;
import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 * 
 */
public class FileItemCategoryDaoImpl extends AbstractDaoImpl<FileItemCategory> implements FileItemCategoryDao {

	@Override
	public Optional<FileItemCategory> findByCategoryAndExtension(String ext, FileCategories category) {

		TypedQuery<FileItemCategory> query = entityManager.createQuery(
				"from FileItemCategory c "
				+ "where c.extension=:extension "
				+ "and c.category=:category",
				FileItemCategory.class);
		
		query.setParameter(1, ext);
		query.setParameter(2, category.name());
		
		return getUniqueResult(query, "More than one category found for: extension=" + ext + ", category=" + category.name());
	}

}
