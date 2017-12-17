package com.file.sharing.core.objects.file;

import com.file.sharing.core.objects.BasicItemInfo;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
public interface BasicFileInfo extends BasicItemInfo {

	/**
	 * @return
	 */
	FileCategories getCategory();

}
