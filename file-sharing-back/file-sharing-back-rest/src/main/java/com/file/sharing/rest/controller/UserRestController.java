package com.file.sharing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.ItemsService;
import com.file.sharing.core.service.UserService;
import com.file.sharing.rest.factory.UserDtoFactory;

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

	@Autowired
	private ItemsService itemService;


	/**
	 * @param email
	 * @return
	 */
	@GetMapping("/me")
	public UserDTO getUser() {
		String email = context.getUserEmail();
		UserInfo userInfo = userService.getUserInfoByEmail(email);
		return userFactory.fromUserInfo(userInfo);
	}
	
	@GetMapping("/test")
	public String test() {

		itemService.createDirectory(101, "justSomeTest", context.getGetUserId());

		return "ok";

	}
	

}
