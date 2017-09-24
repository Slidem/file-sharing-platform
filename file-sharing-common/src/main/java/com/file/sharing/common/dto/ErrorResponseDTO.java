package com.file.sharing.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Alexandru
 *
 */
@JsonInclude(Include.NON_NULL)
public class ErrorResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2357844291086766422L;

	private int code;

	private String message;

	private String description;

	/**
	 * 
	 * @param code
	 * @param message
	 * @param description
	 */
	public ErrorResponseDTO(int code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
