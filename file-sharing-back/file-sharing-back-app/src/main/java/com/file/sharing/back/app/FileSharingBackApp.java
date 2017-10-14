package com.file.sharing.back.app;

import static com.file.sharing.back.app.constants.FileSharingBackConstants.PROFILE_PROD;
import static com.file.sharing.back.app.constants.FileSharingBackConstants.SPRING_ACTIVE_PROFILE;
import static com.file.sharing.back.app.constants.FileSharingBackConstants.SPRING_CONFIG_FILE;
import static java.lang.System.getProperty;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.file.sharing.back.app.config.FileSharingBackPropertiesConfig;

@SpringBootApplication(scanBasePackageClasses = { FileSharingBackPropertiesConfig.class }, exclude = {
		HibernateJpaAutoConfiguration.class })
public class FileSharingBackApp {
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(FileSharingBackApp.class);

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
