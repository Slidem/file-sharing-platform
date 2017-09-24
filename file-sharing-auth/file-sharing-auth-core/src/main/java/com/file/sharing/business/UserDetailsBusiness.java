package com.file.sharing.business;

import org.springframework.security.core.userdetails.UserDetails;

import com.file.sharing.entities.impl.BaseUser;

/**
 * @author Alexandru
 *
 */
public interface UserDetailsBusiness {

	public UserDetails getUserDetails(String username);

	public UserDetails getUserDetailsFromBaseUser(BaseUser baseUser);
}
