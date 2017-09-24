package com.file.sharing.controllers.validators;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.file.sharing.model.UserDTO;

@Component
public class UserDTOValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private final Pattern pattern;

	public UserDTOValidator() {
		this.pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (!(target instanceof UserDTO)) {
			throw new IllegalArgumentException(
					"Whoops, looks like you sent the wrong object. Expected: " + UserDTO.class);
		}

		UserDTO userDTO = (UserDTO) target;
		validateName(userDTO.getFirstName(), "firstName", errors);
		validateName(userDTO.getLastName(), "lastName", errors);
		validateEmail(userDTO.getEmail(), errors);
		validatePassowrd(userDTO.getPassword(), errors);
	}

	private void validateName(String name, String fieldName, Errors errors) {
		if (StringUtils.isEmptyOrWhitespace(name) || name.length() < 5) {
			errors.rejectValue(fieldName, "Field cannot be empty or have less than 5 characters");
		}
	}

	private void validateEmail(String email, Errors errors) {
		checkForEmptyField(email, "email", errors);
		if (!pattern.matcher(email).matches()) {
			errors.rejectValue(email, "Invalid email format");
		}
	}

	private void validatePassowrd(String password, Errors errors) {
		checkForEmptyField(password, "password", errors);

	}

	private void checkForEmptyField(String filed, String fieldName, Errors errors) {
		if (StringUtils.isEmptyOrWhitespace(filed)) {
			errors.rejectValue(fieldName, "Field cannot be empty");
		}
	}

}
