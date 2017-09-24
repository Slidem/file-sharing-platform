package com.file.sharing.validators.impl;

import static com.file.sharing.common.validation.ValidationConstants.EMAIL_PATTERN;

import org.springframework.stereotype.Component;

import com.file.sharing.validators.RequestValidator;

@Component
public class RequestValidatorImpl implements RequestValidator {

	/**
	 * @see filesharing.be.validators.RequestValidator#isValidEmail(java.lang.String)
	 */
	@Override
	public boolean isValidEmail(String email) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(EMAIL_PATTERN);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

}