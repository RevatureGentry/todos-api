package com.revature.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author William Gentry
 */
@RestControllerAdvice
public class RESTExceptionHandler {

	@ExceptionHandler({ PathVariableExpectedException.class, TodoNotFoundException.class })
	public ResponseEntity<Map<String, String>> handleException(ApiException exception) {
		return ResponseEntity.status(exception.getStatus()).body(exception.getErrorMessage());
	}

}
