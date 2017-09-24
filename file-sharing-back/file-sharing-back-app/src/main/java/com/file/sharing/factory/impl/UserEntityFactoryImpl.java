package com.file.sharing.factory.impl;

import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.entities.AccStats;
import com.file.sharing.entities.Role;
import com.file.sharing.entities.User;
import com.file.sharing.factory.UserEntityFactory;

@Component
public class UserEntityFactoryImpl implements UserEntityFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * filesharing.be.builders.UserEntityFactory#fromDto(filesharing.commons.dto.
	 * UserDTO)
	 */
	@Override
	public User fromDto(UserDTO dto) {
		Objects.requireNonNull(dto);

		AccStats accSts = new AccStats(dto.getType(), dto.getStatus());
		Role role = new Role(dto.getRole().getId(), dto.getRole());

		final String password = new BCryptPasswordEncoder().encode(dto.getPassword());

		User user = new User(dto.getId(), dto.getName(), dto.getSurname(), password, dto.getEmail(), dto.getPicture(),
				accSts, role);

		return user;
	}

}
