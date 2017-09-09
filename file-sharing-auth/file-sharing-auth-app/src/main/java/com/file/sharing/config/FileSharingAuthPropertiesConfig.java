package com.file.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.file.sharing.FileSharingAuthApp;

/**
 * @author Alexandru
 *
 *         Configuration class that loads the main properties file. the
 *         app.config.file is a environment variable indicating the path to the
 *         config file. The app.config.file variable is resolved at runtime,
 *         based on the spring profile (dev or prod)
 * 
 * @see FileSharingAuthApp
 *
 */
@Configuration
@PropertySource("${app.config.file}")
public class FileSharingAuthPropertiesConfig {

}
