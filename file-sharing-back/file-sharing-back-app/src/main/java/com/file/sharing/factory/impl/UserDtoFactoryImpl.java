package com.file.sharing.factory.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.entities.AccStats;
import com.file.sharing.entities.Role;
import com.file.sharing.entities.User;
import com.file.sharing.factory.UserDtoFactory;

/**
 * @author Alexandru
 *
 */
@Component
public class UserDtoFactoryImpl implements UserDtoFactory {

	/**
	 * @see filesharing.be.factory.UserDtoFactory#fromEntity(filesharing.be.entities.User)
	 */
	@Override
	public UserDTO fromEntity(User user) {
		Objects.requireNonNull(user);
		AccStats accountStatus = Objects.requireNonNull(user.getAccStats());
		Role role = Objects.requireNonNull(user.getRole());

		UserDTO.Builder builder = new UserDTO.Builder();
		builder.withName(user.getName()).withSurname(user.getSurname()).withEmail(user.getEmail())
				.withPassword(user.getPassword()).withPicture(user.getPicture()).withRole(role.getRole())
				.withStatus(accountStatus.getStatus()).withType(accountStatus.getType());

		return builder.build();
	}

	@Override
	public UserDTO fromEntityWithoutPassword(User user) {
		Objects.requireNonNull(user);
		AccStats accountStatus = Objects.requireNonNull(user.getAccStats());
		Role role = Objects.requireNonNull(user.getRole());

		UserDTO.Builder builder = new UserDTO.Builder();
		builder.withName(user.getName()).withSurname(user.getSurname()).withEmail(user.getEmail())
				.withPicture(user.getPicture()).withRole(role.getRole()).withStatus(accountStatus.getStatus())
				.withType(accountStatus.getType());

		return builder.build();
	}

}
