package com.file.sharing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.file.sharing.AuthCoreBasePackagesScan;

@Configuration
@ComponentScan(basePackageClasses = { AuthCoreBasePackagesScan.class })
public class AuthConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public void init(ApplicationContext applicationContext) {
		logger.info("Module auth-core has been added.");
	}

}
