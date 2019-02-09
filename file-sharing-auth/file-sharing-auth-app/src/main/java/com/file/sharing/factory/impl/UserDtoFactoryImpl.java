package com.file.sharing.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.file.sharing.business.FileSharingPassEncoder;
import com.file.sharing.entities.AccountInfo;
import com.file.sharing.entities.impl.User;
import com.file.sharing.factory.UserDtoFactory;
import com.file.sharing.model.UserDTO;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;
import com.file.sharing.objects.Role;
import com.file.sharing.service.AccountInfoService;

@Component
public class UserDtoFactoryImpl implements UserDtoFactory {

	private final PasswordEncoder passwordEncoder;

	private final AccountInfoService accountInfoService;

	@Autowired
	public UserDtoFactoryImpl(AccountInfoService accountInfoService, FileSharingPassEncoder fileSharingPassEncoder) {
		this.passwordEncoder = fileSharingPassEncoder.getPassowrdEncoder();
		this.accountInfoService = accountInfoService;
	}

	@Override
	public User toUser(UserDTO userDTO) {
		Objects.requireNonNull(userDTO);
		//@formatter:off
		return new User.UserBuilder().setAccountInfo(getAccountInfo())
									      .setEmail(userDTO.getEmail())
									      .setName(userDTO.getFirstName())
									      .setSurname(userDTO.getLastName())
									      .setPassword(encodePass(userDTO.getPassword()))
									      .setRole(Role.ROLE_USER)
									      .build();
		//@formatter:on							     
	}

	private String encodePass(final String password) {
		return passwordEncoder.encode(password);
	}

	private AccountInfo getAccountInfo() {
		return accountInfoService.createOrGet(AccountStatus.ACTIVE, AccountType.REGULAR.getSubscriptionId());
	}

}
