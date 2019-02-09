package com.file.sharing.core.objects;


import com.file.sharing.core.entity.Subscription;

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

}
