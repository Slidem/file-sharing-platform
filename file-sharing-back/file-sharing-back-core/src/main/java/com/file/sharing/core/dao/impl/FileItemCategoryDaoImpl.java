package com.file.sharing.core.dao.impl;

import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.FileItemCategoryDao;
import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 * 
 */

@Repository
public class FileItemCategoryDaoImpl extends AbstractDaoImpl<FileItemCategory> implements FileItemCategoryDao {

	@Override
	public Optional<FileItemCategory> findByCategoryAndExtension(String ext, FileCategories category) {

		TypedQuery<FileItemCategory> query = entityManager.createQuery(
				"from FileItemCategory c "
				+ "where c.extension=:extension "
				+ "and c.category=:category",
				FileItemCategory.class);
		
		query.setParameter("extension", ext);
		query.setParameter("category", category);
		
		return getUniqueResult(query, "More than one category found for: extension=" + ext + ", category=" + category.name());
	}

}
