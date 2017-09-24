package com.file.sharing.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.file.sharing.common.dto.ErrorResponseDTO;

@ControllerAdvice
public class FileSharingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UserNotFoundException.class })
	protected ResponseEntity<Object> handle(UserNotFoundException ex, WebRequest request) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getErrorCode(), ex.getMessage(), "Description");
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
