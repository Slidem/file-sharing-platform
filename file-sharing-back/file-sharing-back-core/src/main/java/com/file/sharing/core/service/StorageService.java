package com.file.sharing.core.service;

import com.file.sharing.core.objects.StorageInfo;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
// TODO: To be implemented by Aioanei Andrei :)
public interface StorageService {

	/**
	 * @param userId
	 *            Cannot be null.
	 * @return Path to the root folder of the user's storage.
	 * 
	 * @throws NullPointerException
	 *             if the userId is null.
	 */
	public String getUserStoragePath(Integer userId);
	
	/**
	 * @param userId
	 * 				Cannot be null.
	 * @return StorageInfo object containing context info about storage.
	 */
	public StorageInfo getUserStorageInfoFromDisk(Integer userId);

	/**
	 * @param userId
	 * 				Cannot be null.
	 * @return StorageInfo object containing context info about storage.
	 */
	public StorageInfo getUserStorageInfoFromDb(Integer userId);

}
