package com.file.sharing.rest.validators.impl;

import static com.file.sharing.common.validation.ValidationConstants.EMAIL_PATTERN;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.file.sharing.rest.validators.RequestValidator;

/**
 * @author Alexandru
 *
 */
@Component
public class RequestValidatorImpl implements RequestValidator {

	private final Pattern pattern;

	public RequestValidatorImpl() {
		this.pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * @see filesharing.be.validators.RequestValidator#isValidEmail(java.lang.String)
	 */
	@Override
	public boolean isValidEmail(String email) {
		return pattern.matcher(email).matches();
	}

}