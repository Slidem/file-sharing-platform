package com.file.sharing.rest.config;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.file.sharing.core.objects.Context;
import com.file.sharing.rest.context.ContextConfigurer;
import com.file.sharing.rest.context.ContextImpl;
import com.file.sharing.rest.context.ContextImpl.ContextBuidler;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
@Configuration
public class FileSharingContextConfig {

	@Autowired
	private List<ContextConfigurer> contextConfigurers;

	@Bean
	@Scope(value = "request", proxyMode = TARGET_CLASS)
	public Context getContext() {
		ContextImpl.ContextBuidler builder = new ContextBuidler();
		contextConfigurers.forEach(c -> c.configure(builder));
		return builder.build();
	}

}
