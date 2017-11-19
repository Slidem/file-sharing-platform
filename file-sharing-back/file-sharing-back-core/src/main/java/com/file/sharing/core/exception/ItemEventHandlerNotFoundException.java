package com.file.sharing.core.exception;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class ItemEventHandlerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5506626102843598883L;

	public ItemEventHandlerNotFoundException() {
		super();
	}

	public ItemEventHandlerNotFoundException(String message) {
		super(message);
	}

}
