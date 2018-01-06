package com.file.sharing.rest.factory;

import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.directory.BasicDirectoryInfo;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.rest.dto.AbstractBasicItemInfoDTO;
import com.file.sharing.rest.dto.directory.BasicDirectoryInfoDTO;
import com.file.sharing.rest.dto.file.BasicFileInfoDTO;

/**
 * @author Alexandru Mihai
 * @created Dec 30, 2017
 * 
 */
public interface ItemBasicInfoDtoFactory {

	/**
	 * @param item
	 * @return
	 */
	AbstractBasicItemInfoDTO toDto(BasicItemInfo itemInfo);

	/**
	 * @param fileItem
	 * @return
	 */
	BasicFileInfoDTO toDto(BasicFileInfo basicFileInfo);

	/**
	 * @param directoryItem
	 * @return
	 */
	BasicDirectoryInfoDTO toDto(BasicDirectoryInfo basicDirectoryInfo);

}
