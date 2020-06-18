package com.revature.todo;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public class PathVariableExpectedException extends ApiException {

	public PathVariableExpectedException(Long id) {
		super(HttpStatus.BAD_REQUEST, "Expected valid path variable but received " + id);
	}
}
