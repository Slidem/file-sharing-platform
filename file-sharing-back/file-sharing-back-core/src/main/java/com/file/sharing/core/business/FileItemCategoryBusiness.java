package com.file.sharing.core.business;

import com.file.sharing.core.entity.FileItemCategory;
import com.file.sharing.core.objects.file.FileCategories;

public interface FileItemCategoryBusiness {

	FileItemCategory getFileItemCategoryFromItemName(String itemName);

	FileItemCategory getFileItemCategory(FileCategories category, String extension);

}
