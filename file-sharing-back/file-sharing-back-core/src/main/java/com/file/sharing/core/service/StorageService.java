package com.file.sharing.core.service;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
// TODO: To be implemented by Aioanei Andrei :)
public interface StorageService {

	/**
	 * @param userId
	 *            Cannot be null.
	 * @return Path to the root folder of the user's stoarage.
	 * 
	 * @throws NullPointerException
	 *             if the userId is null.
	 */
	public String getStoragePath(Integer userId);

}
