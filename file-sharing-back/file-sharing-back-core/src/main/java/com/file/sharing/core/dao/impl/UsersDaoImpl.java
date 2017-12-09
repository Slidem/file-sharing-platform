package com.file.sharing.core.dao.impl;

import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.UsersDao;
import com.file.sharing.core.entity.User;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Repository
public class UsersDaoImpl extends AbstractDaoImpl<User> implements UsersDao {

	@Override
	public Optional<User> findByEmail(String email) {
		TypedQuery<User> query = entityManager.createQuery("from User u where u.email=:email", User.class);
		query.setParameter("email", email);
		return getUniqueResult(query, "More than one user found for email: " + email);
	}

}
