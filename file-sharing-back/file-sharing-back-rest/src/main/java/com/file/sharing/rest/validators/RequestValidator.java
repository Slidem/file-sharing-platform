package com.file.sharing.rest.validators;

public interface RequestValidator {

	/**
	 * @param email
	 * @return
	 */
	public boolean isValidEmail(String email);
}
