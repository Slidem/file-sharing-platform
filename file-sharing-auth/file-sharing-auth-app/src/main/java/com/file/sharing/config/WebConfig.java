package com.file.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	//@formatter:off
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 
			"classpath:/META-INF/resources/",
			"classpath:/resources/",
			"classpath:/static/", 
			"classpath:/META-INF/resources/webjars/" 
    };
	//@formatter:on

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
				.setCacheControl(CacheControl.noCache());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("login").setViewName("login");
		// TODO:To be removed / Add some basic bage...
		registry.addViewController("/").setViewName("index");
		// Sign-up page
		registry.addViewController("signUp").setViewName("signUp");
	}
}