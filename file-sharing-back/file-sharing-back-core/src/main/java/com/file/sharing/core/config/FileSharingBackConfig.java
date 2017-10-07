package com.file.sharing.core.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.file.sharing.core.FileSharingBackCoreBasePackageScan;

/**
 * @author Alexandru
 *
 */
@Configuration
@ComponentScan(basePackageClasses = { FileSharingBackCoreBasePackageScan.class })
public class FileSharingBackConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSharingBackConfig.class);

	@PostConstruct
	public void init() {
		LOGGER.info("Added file sharing back-end core module");
	}
}
