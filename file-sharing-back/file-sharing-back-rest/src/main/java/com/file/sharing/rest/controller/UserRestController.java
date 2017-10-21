package com.file.sharing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.common.user.AccountType;
import com.file.sharing.core.entity.AccStats;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.exception.FileSharingException;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.UserService;
import com.file.sharing.rest.factory.UserDtoFactory;
import com.file.sharing.rest.validators.RequestValidator;

/***
 * 
 * @author Alexandru
 *
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

	private UserService userService;

	private RequestValidator requestValidator;

	private UserDtoFactory userDtoFactory;

	@Autowired
	public UserRestController(UserService userService, RequestValidator requestValidator,
			UserDtoFactory userDtoFactory) {
		this.userService = userService;
		this.requestValidator = requestValidator;
		this.userDtoFactory = userDtoFactory;
	}

	/**
	 * @param email
	 * @return
	 */
	@GetMapping
	public UserDTO getUserByEmail(@RequestParam(value = "email", required = true) String email) {
		if (!requestValidator.isValidEmail(email)) {
			throw new FileSharingException();
		}
		UserInfo userInfo = userService.getUserInfoByEmail(email);
		return userDtoFactory.fromUserInfo(userInfo);
	}

	@GetMapping
	@RequestMapping("/save")
	public void saveUser(@RequestParam(value = "email", required = true) String email) {
		User user = new User();
		user.setEmail(email);
		user.setName("DummyName");
		user.setSurname("DummySurname");
		user.setAccStats(new AccStats(AccountType.REGULAR, AccountStatus.ACTIVE));
		user.setPassword("parola");
		user.setRole();
		userService.saveUser(user);
	}

}
