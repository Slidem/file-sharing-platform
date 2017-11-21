package com.file.sharing.core.service;

import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.objects.UserInfo;

public interface UserService {

	/**
	 * @param email
	 * @return
	 * 
	 * @throws UserNotFoundException if the user was not found for the given email
	 */
	public UserInfo getUserInfoByEmail(String email);

	/**
	 * 
	 * @param userId
	 * @return
	 * 
	 *  @throws UserNotFoundException if the user was not found for the given id
	 */
	public UserInfo getUserInfoByUserId(Integer userId);
}
