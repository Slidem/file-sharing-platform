package com.file.sharing.common.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Alexandru
 *
 */
@JsonInclude(Include.NON_NULL)
public class ErrorResponseDTO {

	private ErrorCode errorCode;

	private String description;

	public ErrorResponseDTO(ErrorCode errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public ErrorCode getError() {
		return errorCode;
	}

	public void setError(String error) {
		this.errorCode = ErrorCode.valueOf(error);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
