package com.file.sharing.core.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.OrderValue;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public interface ItemService {

	/**
	 * @param itemId
	 * @return the full path of the item. For example, if an <b>item</b> is under
	 *         the directory <b>/dir/test/</b> and the item name is <b>testItem</b>,
	 *         the full path of the item will be <b><i>/dir/test/testItem</i></b>.
	 */
	String getItemFullPath(int itemId);

	/**
	 * @param itemId
	 * @return
	 */
	ItemDetails getItemDetails(int itemId) throws IOException;

	/**
	 * @param directoryId
	 * @return
	 */
	DirectoryDetails getDirectoryDetails(int directoryId) throws IOException;

	/**
	 * @param fileId
	 * @return
	 */
	FileDetails getFileDetails(int fileId) throws IOException;

	/**
	 * @param fileId
	 * @return
	 */
	File retrieveFile(Integer fileId) throws IOException;

	/**
	 * @param itemSearch
	 * @return
	 */
	PageResult<BasicFileInfo> searchFiles(ItemSearch itemSearch);

	/**
	 * @param itemId
	 * @return
	 */
	List<ItemActionInfo> getItemActionsInfo(Integer itemId);

	/**
	 * @param itemId
	 * @param orderValue
	 * @return
	 */
	List<ItemActionInfo> getItemActionsInfo(Integer itemId, OrderValue orderValue);

}
