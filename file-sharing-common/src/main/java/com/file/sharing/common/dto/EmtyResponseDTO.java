package com.file.sharing.common.dto;

public class EmtyResponseDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2410866165886507655L;

	private String message;

	/**
	 * 
	 */
	public EmtyResponseDTO() {
	}

	/**
	 * @param code
	 */
	public EmtyResponseDTO(int code) {
		super.code = code;
	}

	/**
	 * @param code
	 * @param message
	 */
	public EmtyResponseDTO(int code, String message) {
		super.code = code;
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
