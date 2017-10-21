package com.file.sharing.controllers;

import static com.file.sharing.constants.FileSharingAuthConstants.AUTH_INFO_ATTR_NAME;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.controllers.validators.UserDTOValidator;
import com.file.sharing.entities.impl.User;
import com.file.sharing.exceptions.UserEmailAlreadyInUseException;
import com.file.sharing.factory.UserDtoFactory;
import com.file.sharing.model.ErrorDTO;
import com.file.sharing.model.UserDTO;
import com.file.sharing.objects.AuthorizationInfo;
import com.file.sharing.service.FileSharingUserService;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@RestController
@RequestMapping("/register")
public class SignUpController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private UserDtoFactory userDtoFactory;

	private UserDTOValidator userDTOValidator;

	private FileSharingUserService userService;

	private String contextPath;

	public SignUpController(UserDtoFactory userDtoFactory, UserDTOValidator userDTOValidator,
			FileSharingUserService userService, Environment environment) {
		this.userDtoFactory = userDtoFactory;
		this.userDTOValidator = userDTOValidator;
		this.userService = userService;
		this.contextPath = environment.getProperty("server.contextPath", "file-sharing-auth");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
		User u = userDtoFactory.toUser(userDTO);
		userService.createUser(u);
		return userDTO;
	}

	@RequestMapping("/login")
	public void goToLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String url = contextPath + "/oauth/authorize";
		AuthorizationInfo authorizationInfo = (AuthorizationInfo)request.getSession().getAttribute(AUTH_INFO_ATTR_NAME);
		if (authorizationInfo != null) {
			url = url + authorizationInfo.getQueryParams();
		}
		response.sendRedirect(url);
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(userDTOValidator);
	}

	// --- Exception handling ----

	@ExceptionHandler({ UserEmailAlreadyInUseException.class })
	@ResponseBody
	public ErrorDTO handleDuplicateEmailException(HttpServletResponse response, UserEmailAlreadyInUseException e) {
		response.setStatus(FORBIDDEN.value());
		return getErrorDTO(e);
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ErrorDTO handleException(HttpServletResponse response, Exception e) {
		logger.info(e.getMessage(), e);
		response.setStatus(INTERNAL_SERVER_ERROR.value());
		return getErrorDTO();
	}

	// ----------------------------

	private ErrorDTO getErrorDTO(UserEmailAlreadyInUseException e) {
		String message = "User email " + e.getEmail() + " already exists.";
		logger.debug(message, e);
		return new ErrorDTO(FORBIDDEN.value(), message);
	}

	private ErrorDTO getErrorDTO() {
		return new ErrorDTO(INTERNAL_SERVER_ERROR.value(), "Internal server error");
	}

}
