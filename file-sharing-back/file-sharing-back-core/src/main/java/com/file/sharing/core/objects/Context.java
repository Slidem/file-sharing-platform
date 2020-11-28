package com.file.sharing.core.objects;


import com.file.sharing.core.entity.Subscription;

import java.util.Map;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
public interface Context {

	/**
	 * @return
	 */
	String getUserEmail();

	/**
	 * @return
	 */
	Integer getGetUserId();

	/**
	 * @return
	 */
	Subscription getUserSubscription();

	/**
	 * @return
	 */
	StorageInfo getUserStorageInfo();

	/**
	 *
	 * @return
	 */
	Map<Long, Long> getUserUsedAndTotalSpace();

}
