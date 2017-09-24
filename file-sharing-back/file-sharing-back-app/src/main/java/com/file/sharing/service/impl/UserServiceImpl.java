package com.file.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.dao.UsersDao;
import com.file.sharing.entities.User;
import com.file.sharing.exceptions.UserNotFoundException;
import com.file.sharing.factory.UserDtoFactory;
import com.file.sharing.factory.UserEntityFactory;
import com.file.sharing.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UsersDao usersDao;

	private UserDtoFactory userDtoFactory;

	private UserEntityFactory userEntityFactory;

	/**
	 * @param usersDao
	 * @param useDtoFactory
	 * @param userEntityFactory
	 */
	@Autowired
	public UserServiceImpl(UsersDao usersDao, UserDtoFactory useDtoFactory, UserEntityFactory userEntityFactory) {
		this.usersDao = usersDao;
		this.userDtoFactory = useDtoFactory;
		this.userEntityFactory = userEntityFactory;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveUser(UserDTO userDTO) {
		User user = userEntityFactory.fromDto(userDTO);
		usersDao.save(user);
	}

	@Override
	public void deleteUser(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDTO findUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getUsers() {
		List<UserDTO> usersDtos = new ArrayList<UserDTO>();
		List<User> users = usersDao.getAll();
		users.stream().forEach(u -> usersDtos.add(userDtoFactory.fromEntity(u)));
		return usersDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findUserByEmail(String email) {
		Optional<User> user = usersDao.findByEmail(email);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User name not found!");
		}
		return userDtoFactory.fromEntityWithoutPassword(user.get());
	}
}
