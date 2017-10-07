package com.file.sharing.controllers;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.controllers.validators.UserDTOValidator;
import com.file.sharing.entities.impl.User;
import com.file.sharing.exceptions.UserEmailAlreadyInUseException;
import com.file.sharing.factory.UserDtoFactory;
import com.file.sharing.model.ErrorDTO;
import com.file.sharing.model.UserDTO;
import com.file.sharing.service.FileSharingUserService;

@RestController
@RequestMapping("/register")
public class SignUpController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDtoFactory userDtoFactory;

	@Autowired
	private UserDTOValidator userDTOValidator;

	@Autowired
	private FileSharingUserService userService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
		User u = userDtoFactory.toUser(userDTO);
		userService.createUser(u);
		return userDTO;
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userDTOValidator);
	}

	@ExceptionHandler({ UserEmailAlreadyInUseException.class })
	public ResponseEntity<ErrorDTO> handleDuplicateEmailException(UserEmailAlreadyInUseException e) {
		return new ResponseEntity<>(getErrorDTO(e), FORBIDDEN);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorDTO> handleException(Exception e) {
		logger.info(e.getMessage(), e);
		return new ResponseEntity<>(getErrorDTO(e), INTERNAL_SERVER_ERROR);
	}

	private ErrorDTO getErrorDTO(UserEmailAlreadyInUseException e) {
		String message = "User email " + e.getEmail() + " already exists.";
		logger.debug(message, e);
		return new ErrorDTO(FORBIDDEN.value(), message);
	}

	private ErrorDTO getErrorDTO(Exception e) {
		return new ErrorDTO(INTERNAL_SERVER_ERROR.value(), "Internal server error");
	}

}
