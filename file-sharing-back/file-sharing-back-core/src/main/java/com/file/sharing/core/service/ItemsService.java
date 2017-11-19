package com.file.sharing.core.service;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public interface ItemsService {

	/**
	 * Creates a directory under the user's root directory.
	 * 
	 * @param directoryName Name of the directory. Cannot be null or empty. 
	 * @param userId Id of the user. Cannot be null;
	 */
	void createDirectory(String directoryName, Integer userId);

	/**
	 * @param parentId Id of the parent directory.
	 * @param directoryName Name of the directory. Cannot be null or empty. 
	 * @param userId Id of the user. Cannot be null;
	 * 	
	 **/
	void createDirectory(Integer parentId, String directoryName, Integer userId);

	/**
	 * @param directoryId
	 * @param userId
	 */
	void deleteDirectory(Integer directoryId, Integer userId);

	/**
	 * @param directoryId
	 * @param newName
	 */
	void renameDirectory(Integer directoryId, Integer userId, String newName);

	/**
	 * @param parentId
	 * @param directoryId
	 * @param userId
	 */
	void moveDirectory(Integer parentId, Integer directoryId, Integer userId);

}
