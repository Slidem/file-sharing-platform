package com.file.sharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.file.sharing.business.FileSharingPassEncoder;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FileSharingPassEncoder fileSharingPassEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(fileSharingPassEncoder.getPassowrdEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/signUp", 
				"/register/login",
				"/register/login*",
				"/register/login/**",
				"/register/login**",
				"/register", 
				"/static/**/*.*",
				"/bootstrap/**", 
				"/jquery/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
				.csrf().disable()
				.formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
				.antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
	}
}