package com.file.sharing.business.impl;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.file.sharing.business.UserDetailsBusiness;
import com.file.sharing.dao.UserDAO;
import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.entities.impl.UserDetailsImpl;
import com.file.sharing.objects.Role;

@Component
public class UserDetailsBusinessImpl implements UserDetailsBusiness {

	private final UserDAO userDao;

	@Autowired
	public UserDetailsBusinessImpl(UserDAO userDao) {
		this.userDao = Objects.requireNonNull(userDao);
	}

	@Override
	public UserDetails getUserDetails(String username) {
		Optional<BaseUser> baseUser = userDao.getBaseUserByMail(requireNonNull(username));
		if (!baseUser.isPresent()) {
			throw new UsernameNotFoundException("User name not found: " + username);
		}
		return getUserDetailsFromBaseUser(baseUser.get());
	}

	@Override
	public UserDetails getUserDetailsFromBaseUser(BaseUser baseUser) {
		Objects.requireNonNull(baseUser);
		return new UserDetailsImpl.Builder()
				.setUserName(baseUser.getEmail())
				.setPassowrd(baseUser.getPassword())
				.setAuthorities(getGrantedAuthorities(baseUser.getRole()))
				// DEFAULTS:
				.setEnabled(true)
				.setCredentialsNonExpired(true)
				.setAccountNonLocked(true)
				.setAccountNonExpired(true)
				.build();

	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Role role) {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

}
