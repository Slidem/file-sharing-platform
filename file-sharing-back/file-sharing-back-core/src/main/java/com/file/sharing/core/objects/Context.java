package com.file.sharing.core.objects;

import com.file.sharing.common.user.AccountType;

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
	AccountType getUserAccountType();

	/**
	 * @return
	 */
	String getUserStorageLocation();

}
