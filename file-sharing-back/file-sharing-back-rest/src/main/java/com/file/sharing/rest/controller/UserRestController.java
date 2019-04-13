package com.file.sharing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.UserService;
import com.file.sharing.rest.factory.UserDtoFactory;

import java.util.Map;

/***
 * 
 * @author Alexandru
 *
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDtoFactory userFactory;

	@Autowired
	private Context context;


	/**
	 * @return UserDTO object for current user
	 */
	@GetMapping("/me")
	public UserDTO getUser() {
		String email = context.getUserEmail();
		UserInfo userInfo = userService.getUserInfoByEmail(email);
		return userFactory.fromUserInfo(userInfo);
	}

	//TODO
	@GetMapping("/space/available")
	public Map<Long, Long> getUserAvailableAndTotalSpace() {

		return context.getUserUsedAndTotalSpace();
	}

	@GetMapping("/subscription")
	public String getUserSubscription() {
		// acc type -> make class that returns size of acct by acc type (from filesystem)
		return userService.getUserSubscription(context.getGetUserId()).toString();
	}
}
