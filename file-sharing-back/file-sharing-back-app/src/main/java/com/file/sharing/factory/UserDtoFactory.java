package com.file.sharing.factory;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.entities.User;

/**
 * @author Alexandru
 *
 */
public interface UserDtoFactory {

	/**
	 * @param user
	 * @return
	 */
	public UserDTO fromEntity(User user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO fromEntityWithoutPassword(User user);

}