package com.file.sharing.core.exception;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8681321608127963106L;

	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message) {
		super(message);
	}
	
	
}
