package com.file.sharing.back.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.UserService;
import com.file.sharing.rest.context.ContextConfigurer;
import com.file.sharing.rest.context.ContextImpl.ContextBuidler;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
@Component
public class PrincipalContextConfigurer implements ContextConfigurer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserService userService;

	@Override
	public void configure(ContextBuidler builder) {

		logger.info("Configuring context through: {}", getClass().getName());

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = (String) principal;
		builder.setUserEmail(email);

		UserInfo userInfo = userService.getUserInfoByEmail(email);

		builder.setUserId(userInfo.getUserId());
		builder.setUserAccountType(userInfo.getAccStatsInfo().getType());

		// TODO: Set also user storage here.
	}

}
