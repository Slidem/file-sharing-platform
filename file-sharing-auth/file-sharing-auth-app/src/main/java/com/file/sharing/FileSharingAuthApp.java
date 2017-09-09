package com.file.sharing;

import static com.file.sharing.constants.FileSharingAuthConstants.PROFILE_PROD;
import static com.file.sharing.constants.FileSharingAuthConstants.SPRING_ACTIVE_PROFILE;
import static com.file.sharing.constants.FileSharingAuthConstants.SPRING_CONFIG_FILE;
import static java.lang.System.getProperty;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.file.sharing.config.FileSharingAuthPropertiesConfig;

/**
 * @author Alexandru
 *
 */
@SpringBootApplication(scanBasePackageClasses = { FileSharingAuthPropertiesConfig.class })
@EnableAutoConfiguration
public class FileSharingAuthApp {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(FileSharingAuthApp.class);

		String defaultPropertiesPath = "classpath:config/dev.properties";

		if (System.getProperty(SPRING_ACTIVE_PROFILE) == null) {
			throw new RuntimeException("Spring profile must be specified as a VM argument (dev or prod).");
		}

		String springProfile = System.getProperty(SPRING_ACTIVE_PROFILE);
		setAppConfig(app, PROFILE_PROD.equals(springProfile) ? getProperty(SPRING_CONFIG_FILE) : defaultPropertiesPath);

		app.run(args);
	}

	private static void setAppConfig(SpringApplication app, String path) {
		Properties properties = new Properties();
		properties.setProperty("app.config.file", path);
		app.setDefaultProperties(properties);
	}
}
