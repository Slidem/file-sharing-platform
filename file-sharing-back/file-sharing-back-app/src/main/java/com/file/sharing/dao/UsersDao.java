package com.file.sharing.dao;

import java.util.Optional;

import com.file.sharing.entities.User;

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
