package com.file.sharing.core.dao;

import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.search.ItemSearch;

/**
 * @author Alexandru Mihai
 * @created Nov 19, 2017
 */
public interface FileItemDao extends AbstractDao<FileItem> {

	PageResult<FileItem> getItemsBasedOnCriteria(ItemSearch itemSearch);

}
