package com.revature.todo;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William Gentry
 */
public class ApiException extends RuntimeException {

	private final HttpStatus status;
	private final Map<String, String> message;

	public ApiException(HttpStatus status, String reason) {
		this.status = status;
		message = Collections.singletonMap("error", reason);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Map<String, String> getErrorMessage() {
		return message;
	}
}
