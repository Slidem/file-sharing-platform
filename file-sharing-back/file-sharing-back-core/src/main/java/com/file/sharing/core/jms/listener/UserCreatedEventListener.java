package com.file.sharing.core.jms.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
	
	@Value("${storage.path}")
	private String storagePath;
	
	/**
	 * Creates root folder on the disk for a newly created user.
	 * Path type: storage.path/userId/
	 * @param event
	 */

	@EventListener
	public void handleUserCreatedEvent(UserCreatedEvent event) {
		logger.info("User created event: {}", event);
		
		//Create user storage
		Path dir = Paths.get(storagePath, event.getUserId().toString());
		try {
			Files.createDirectories(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}