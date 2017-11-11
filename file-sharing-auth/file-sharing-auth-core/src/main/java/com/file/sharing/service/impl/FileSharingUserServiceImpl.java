package com.file.sharing.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.business.UserBusiness;
import com.file.sharing.config.Constants;
import com.file.sharing.entities.impl.User;
import com.file.sharing.jms.commons.object.JmsUserInfo;
import com.file.sharing.jms.events.UserCreatedEvent;
import com.file.sharing.service.FileSharingUserService;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
@Service
@Transactional(value = Constants.PG_JDBC_TX_NAME, readOnly = false)
public class FileSharingUserServiceImpl implements FileSharingUserService {

	private final UserBusiness userBusiness;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	public FileSharingUserServiceImpl(UserBusiness userBusiness, ApplicationEventPublisher eventPublisher) {
		this.userBusiness = userBusiness;
		this.eventPublisher = eventPublisher;
	}


	@Override
	public User createUser(User user) {
		User u = userBusiness.createUser(user);

		JmsUserInfo jmsUserInfo = new JmsUserInfo(u.getId(), u.getEmail(), Instant.now());

		eventPublisher.publishEvent(new UserCreatedEvent(jmsUserInfo));

		return u;
	}


}
