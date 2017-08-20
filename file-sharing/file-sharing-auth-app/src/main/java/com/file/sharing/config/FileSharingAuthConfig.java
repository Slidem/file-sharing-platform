package com.file.sharing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FileSharingAuthConfig {

	private Logger logger = LoggerFactory.getLogger(FileSharingAuthConfig.class);

	// FIXME: remove or replace with usefull code :) . This now servers only as
	// test code

	@Autowired
	public void init ( Environment env) {
		logger.info("Application loaded! Server port: {}", env.getProperty("server.port"));
	}

}
