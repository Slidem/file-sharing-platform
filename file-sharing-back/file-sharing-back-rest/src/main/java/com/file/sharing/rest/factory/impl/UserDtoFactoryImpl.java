package com.file.sharing.rest.factory.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.rest.factory.UserDtoFactory;

/**
 * @author Alexandru
 *
 */
@Component
public class UserDtoFactoryImpl implements UserDtoFactory {

	@Override
	public UserDTO fromUserInfo(UserInfo userInfo) {
		Objects.requireNonNull(userInfo);
		return new UserDTO.Builder()
				.withEmail(userInfo.getEmail())
				.withName(userInfo.getName())
				.withSurname(userInfo.getSurname())
				.withPicture(userInfo.getPicture())
				.withRole(userInfo.getRole())
				.withStatus(userInfo.getAccStatsInfo().getStatus())
				.withType(userInfo.getAccStatsInfo().getType())
				.build();
	}

}
