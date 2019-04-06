package com.file.sharing.core.jms.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.file.sharing.core.business.UserStorageBusiness;
import com.file.sharing.core.entity.UserStorage;
import com.file.sharing.core.service.UserStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.file.sharing.core.jms.event.UserCreatedEvent;

/**
 * 
 * @author Andrei Aioanei
 * @created 30 Nov 2017
 * 
 */

@Component
public class UserCreatedEventListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	UserStorageService userStorageService;

	@Autowired
	public UserCreatedEventListener(UserStorageService userStorageService) {
		this.userStorageService = userStorageService;
	}
	
	@Value("${storage.path}")
	private String storagePath;

	/**
	 * Creates root folder on the disk and a UserStorage entry in the database for a newly created user.
	 * Path type: storage.path/userId/
	 * @param event
	 */

	@EventListener
	public void handleUserCreatedEvent(UserCreatedEvent event) {
		logger.info("User created event: {}", event);

		userStorageService.createUserStorageByUserId(event.getUserId());

		Path dir = Paths.get(storagePath, event.getUserId().toString());

		try {
			Files.createDirectories(dir);
		} catch (IOException e) {
			logger.warn("Unable to create directory for user created event {}", event);
			logger.warn(e.getMessage(), e);
		}
	}
}