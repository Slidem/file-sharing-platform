package com.file.sharing.rest.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.file.sharing.rest.FileSharingBackRestPackageScan;

/**
 * @author Alexandru
 *
 */
@Configuration
@ComponentScan(basePackageClasses = { FileSharingBackRestPackageScan.class })
public class FileSharingBackRestConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSharingBackRestConfig.class);

	@PostConstruct
	public void init() {
		LOGGER.info("Added file sharing backend rest module");
	}

}
