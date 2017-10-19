package com.file.sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@Configuration
@Order(value = Ordered.LOWEST_PRECEDENCE - 10)
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

	/*
	 * 
	 * 
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

}