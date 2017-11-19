package com.file.sharing.business.impl;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.file.sharing.business.UserBusiness;
import com.file.sharing.dao.UserDAO;
import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.entities.impl.User;
import com.file.sharing.exceptions.UserEmailAlreadyInUseException;

@Component
public class UserBusinessImpl implements UserBusiness {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserDAO userDao;

	@Autowired
	public UserBusinessImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public Integer createUser(User user) {
		String email = Objects.requireNonNull(user).getEmail();
		Optional<BaseUser> baseUser = userDao.getBaseUserByMail(email);
		if (baseUser.isPresent()) {
			throw new UserEmailAlreadyInUseException(email);
		}
		try {
			return userDao.createUser(user);
		} catch (DataIntegrityViolationException e) {
			logger.info(e.getMessage());
			throw new UserEmailAlreadyInUseException(email);
		}
	}
}
