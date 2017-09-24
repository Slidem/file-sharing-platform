package com.file.sharing.factory;

import com.file.sharing.entities.impl.User;
import com.file.sharing.model.UserDTO;

public interface UserDtoFactory {

	User toUser(UserDTO userDTO);

}
