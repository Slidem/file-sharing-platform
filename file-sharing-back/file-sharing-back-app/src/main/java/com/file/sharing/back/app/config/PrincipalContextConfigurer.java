package com.file.sharing.back.app.config;

import com.file.sharing.core.caching.CachedContextProvider;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.StorageService;
import com.file.sharing.core.service.UserService;
import com.file.sharing.rest.context.ContextConfigurer;
import com.file.sharing.rest.context.ContextImpl.ContextBuidler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
@Component
public class PrincipalContextConfigurer implements ContextConfigurer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserService userService;
	private StorageService storageService;
	private CachedContextProvider cachedContextProvider;

	@Autowired
	public PrincipalContextConfigurer(UserService userService, StorageService storageService, CachedContextProvider cachedContextProvider) {
		this.userService = userService;
		this.storageService = storageService;
		this.cachedContextProvider = cachedContextProvider;
	}

	@Override
	public void configure(ContextBuidler builder) {

		logger.info("Configuring context through: {}", getClass().getName());

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = (String) principal;
		builder.setUserEmail(email);

		UserInfo userInfo = cachedContextProvider.getUserInfo(email);

		builder.setUserId(userInfo.getUserId());
		builder.setUserSubscription(userInfo.getAccStatsInfo().getSubscription());
		builder.setUserStorageInfo(cachedContextProvider.getStorageInfo(userInfo.getUserId()));
	}

}
