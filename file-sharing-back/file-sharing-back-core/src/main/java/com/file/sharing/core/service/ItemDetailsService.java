package com.file.sharing.core.service;

import java.nio.file.Path;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public interface ItemDetailsService {

	/**
	 * @param itemId
	 * @return
	 */
	public Path getItemPath(Integer itemId);

}
