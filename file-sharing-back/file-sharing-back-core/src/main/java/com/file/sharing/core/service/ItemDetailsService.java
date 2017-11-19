package com.file.sharing.core.service;

import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileDetails;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public interface ItemDetailsService {

	/**
	 * @param itemId
	 * @return the full path of the item. For example, if an <b>item</b> is under
	 *         the directory <b>/dir/test/</b> and the item name is <b>testItem</b>,
	 *         the full path of the item will be <b><i>/dir/test/testItem</i></b>.
	 */
	String getItemFullPath(int itemId);

	/**
	 * @param directoryId
	 * @return
	 */
	DirectoryDetails getDirectoryDetails(int directoryId);

	/**
	 * @param fileId
	 * @return
	 */
	FileDetails getFileDetails(int fileId);

}
