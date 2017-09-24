package com.file.sharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.business.UserBusiness;
import com.file.sharing.config.Constants;
import com.file.sharing.entities.impl.User;
import com.file.sharing.service.FileSharingUserService;

@Service
@Transactional(value = Constants.PG_JDBC_TX_NAME, readOnly = false)
public class FileSharingUserServiceImpl implements FileSharingUserService {

	private final UserBusiness userBusiness;

	@Autowired
	public FileSharingUserServiceImpl(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}

	@Override
	public User createUser(User user) {
		return userBusiness.createUser(user);
	}

}
