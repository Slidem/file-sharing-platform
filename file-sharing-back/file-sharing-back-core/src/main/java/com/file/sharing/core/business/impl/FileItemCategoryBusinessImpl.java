package com.file.sharing.core.business.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.file.sharing.core.business.FileItemCategoryBusiness;
import com.file.sharing.core.dao.FileItemCategoryDao;
import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.core.utils.FileCategoryUtil;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 * 
 */
@Component
public class FileItemCategoryBusinessImpl implements FileItemCategoryBusiness {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private FileItemCategoryDao fileItemCategoryDao;

	@Autowired
	public FileItemCategoryBusinessImpl(FileItemCategoryDao fileItemCategoryDao) {
		this.fileItemCategoryDao = fileItemCategoryDao;
	}

	@Override
	public FileItemCategory getFileItemCategoryFromItemName(String itemName) {

		String extension = FileCategoryUtil.getExtensionFromFileName(itemName);
		FileCategories category = FileCategoryUtil.getCategoryBasedOnExtension(extension);

		return getFileItemCategory(category, extension);
	}

	@Override
	public FileItemCategory getFileItemCategory(FileCategories category, String extension) {

		Optional<FileItemCategory> result = fileItemCategoryDao.findByCategoryAndExtension(extension, category);
		if (result.isPresent()) {
			return result.get();
		}

		try {
			FileItemCategory fileItemCategory = new FileItemCategory();
			fileItemCategory.setCategory(category);
			fileItemCategory.setExtension(extension);

			Integer id = (Integer) fileItemCategoryDao.saveAndGetId(fileItemCategory);
			fileItemCategoryDao.flush();

			fileItemCategory.setId(id);

			return fileItemCategory;

		} catch (DataIntegrityViolationException e) {
			logger.info(e.getMessage(), e);
			return fileItemCategoryDao.findByCategoryAndExtension(extension, category).orElse(null);
		}

	}

}
