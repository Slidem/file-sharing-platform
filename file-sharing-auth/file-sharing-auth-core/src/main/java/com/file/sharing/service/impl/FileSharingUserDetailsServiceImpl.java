package com.file.sharing.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.business.UserDetailsBusiness;
import com.file.sharing.config.Constants;

@Service
@Transactional(value = Constants.PG_JDBC_TX_NAME, readOnly = true)
public class FileSharingUserDetailsServiceImpl implements UserDetailsService {

	private final UserDetailsBusiness userDetailsBusiness;

	@Autowired
	public FileSharingUserDetailsServiceImpl(UserDetailsBusiness userDetailsBusiness) {
		this.userDetailsBusiness = Objects.requireNonNull(userDetailsBusiness);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetailsBusiness.getUserDetails(username);
	}

}
