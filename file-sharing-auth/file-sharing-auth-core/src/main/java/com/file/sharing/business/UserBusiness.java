package com.file.sharing.business;

import com.file.sharing.entities.impl.User;

/**
 * @author Alexandru Mihai
 * @created Nov 17, 2017
 */
public interface UserBusiness {

	/**
	 * @param user
	 * @return id of the newly created user
	 */
	Integer createUser(User user);

}
