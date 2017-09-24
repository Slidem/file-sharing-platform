package com.file.sharing.service;

import java.util.List;

import com.file.sharing.common.dto.UserDTO;

public interface UserService {

	void saveUser(UserDTO userDTO);

	void deleteUser(UserDTO userDTO);

	UserDTO findUser(Integer id);

	UserDTO findUserByEmail(String email);

	List<UserDTO> getUsers();

}