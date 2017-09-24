package com.file.sharing.factory;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.entities.User;

/**
 * @author Alexandru
 *
 */
public interface UserEntityFactory {

	/**
	 * @param userDTO
	 * @return
	 */
	public User fromDto(UserDTO userDTO);

}
