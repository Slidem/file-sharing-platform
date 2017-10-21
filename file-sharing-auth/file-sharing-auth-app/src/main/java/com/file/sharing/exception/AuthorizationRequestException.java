package com.file.sharing.exception;

import static java.lang.String.format;

import java.util.Arrays;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
public class AuthorizationRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -907624392679779828L;

	private static final String REQUIRED_PARAMETERS_MESSAGE = "Authentication request is missing required parameters %s";

	public AuthorizationRequestException() {
	}

	public AuthorizationRequestException(String message) {
		super(message);
	}

	public AuthorizationRequestException(String... requiredParameters) {
		super(format(REQUIRED_PARAMETERS_MESSAGE, Arrays.toString(requiredParameters)));
	}

}
