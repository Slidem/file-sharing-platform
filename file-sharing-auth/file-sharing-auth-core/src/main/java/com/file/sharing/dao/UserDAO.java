package com.file.sharing.dao;

import java.util.Optional;

import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.entities.impl.User;

/**
 * @author Alexandru
 *
 */
public interface UserDAO {

	/**
	 * Queries the db and returns the basic user information: id, email(which is the
	 * login name), role.
	 * 
	 * @param id
	 * @return basic user information.
	 */
	Optional<BaseUser> getBaseUserById(Integer id);

	/**
	 * @param email
	 * @return
	 */
	Optional<BaseUser> getBaseUserByMail(String email);

	/**
	 * Inserts a new user into the db.
	 * 
	 * @param user
	 * @return
	 */
	User createUser(User user);

}
