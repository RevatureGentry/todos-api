package com.revature.todo;

import org.springframework.http.HttpStatus;

/**
 * @author William Gentry
 */
public class TodoNotFoundException extends ApiException {

	public TodoNotFoundException(Long id) {
		super(HttpStatus.NOT_FOUND, "Failed to find todo with id " + id);
	}
}
