package com.file.sharing.rest.exception;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.file.sharing.common.dto.error.ErrorCode;
import com.file.sharing.common.dto.error.ErrorResponseDTO;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.exception.UserStorageNotFoundException;

/**
 * @author Alexandru
 *
 */
@ControllerAdvice
public class FileSharingExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = { InvalidEmailException.class })
	@ResponseBody
	protected ErrorResponseDTO handle(InvalidEmailException ex, HttpServletResponse response) {
		logger.info(ex.getMessage(), ex);
		response.setStatus(SC_BAD_REQUEST);
		return new ErrorResponseDTO(ErrorCode.INVALID_EMAIL, "Invalid email format.");
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseBody
	protected ErrorResponseDTO handle(UserNotFoundException ex, HttpServletResponse response) {
		logger.info(ex.getMessage(), ex);
		response.setStatus(SC_NOT_FOUND);
		return new ErrorResponseDTO(ErrorCode.USER_NOT_FOUND, "User not found.");
	}
	
	@ExceptionHandler(value = { UserStorageNotFoundException.class })
	@ResponseBody
	protected ErrorResponseDTO handle(UserStorageNotFoundException ex, HttpServletResponse response) {
		logger.info(ex.getMessage(), ex);
		response.setStatus(SC_NOT_FOUND);
		return new ErrorResponseDTO(ErrorCode.USER_STORAGE_NOT_FOUND, "User storage not found.");
	}

}
