package com.file.sharing.core.factory.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.core.entity.AccStats;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.factory.UserInfoFactory;
import com.file.sharing.core.objects.AccStatsInfo;
import com.file.sharing.core.objects.UserInfo;

/**
 * @author Alexandru
 *
 */
@Component
public class UserInfoFactoryImpl implements UserInfoFactory {

	@Override
	public UserInfo fromEntity(User user) {
		return new UserInfo.Builder()
				.setUserId(user.getId())
				.setName(user.getName())
				.setSurname(user.getSurname())
				.setRole(user.getRole().getRole())
				.setPicture(user.getPicture())
				.setAccStatsInfo(getAccountStatusInfo(user.getAccStats()))
				.setEmail(user.getEmail())
				.build();
	}

	private AccStatsInfo getAccountStatusInfo(AccStats accStats) {
		if (accStats == null) {
			return null;
		}
		return new AccStatsInfo(accStats.getType(), accStats.getStatus());
	}

}
