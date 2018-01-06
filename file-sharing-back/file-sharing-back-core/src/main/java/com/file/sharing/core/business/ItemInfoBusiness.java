package com.file.sharing.core.business;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.file.sharing.core.objects.BasicItemInfo;
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
 * @created Dec 26, 2017
 * 
 */
public interface ItemInfoBusiness {
	
	/**
	 * @param itemSearch
	 * @return
	 */
	public PageResult<BasicFileInfo> getFilesInfo(ItemSearch itemSearch);

	/**
	 * @param itemId
	 * @return
	 */
	public Optional<BasicItemInfo> getBasicItemInfo(Integer itemId);

	/**
	 * @param itemId
	 * @return
	 */
	public List<ItemActionInfo> getItemActionsInfo(Integer itemId, OrderValue actionTimeOrder);

	/**
	 * @param itemId
	 * @return
	 */
	public ItemDetails getItemDetails(Integer itemId) throws IOException;

	/**
	 * @param directoryId
	 * @return
	 */
	public DirectoryDetails getDirectoryDetails(Integer directoryId) throws IOException;

	/**
	 * @param fileDetails
	 * @return
	 */
	public FileDetails getFileDetails(Integer fileDetails) throws IOException;

}
