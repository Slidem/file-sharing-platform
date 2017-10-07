package com.file.sharing.core.dao;

import java.util.Optional;

import com.file.sharing.core.entity.User;

/**
 * @author Alexandru
 *
 */
public interface UsersDao extends AbstractDao<User> {

	/**
	 * @param email
	 * @return
	 */
	public Optional<User> findByEmail(String email);

}
