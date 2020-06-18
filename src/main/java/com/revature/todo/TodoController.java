package com.revature.todo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author William Gentry
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@ApiOperation(value = "View a list of all Todos", response = Todo.class, responseContainer = "List")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved all available Todos")
	})
	@GetMapping
	public ResponseEntity<List<Todo>> findAll() {
		return ResponseEntity.ok(todoService.findAllTodos());
	}

	@ApiOperation(value = "View a Todo by ID", response = Todo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully found Todo by provided ID"),
			@ApiResponse(code = 404, message = "Unable to find Todo by provided ID")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Todo> findById(@PathVariable("id") Long id) {
		if (id != null && id > 0) {
			return ResponseEntity.ok(todoService.findById(id));
		}
		throw new PathVariableExpectedException(id);
	}

	@ApiOperation(value = "Create a Todo from a TodoFrom")
	@ApiResponses(value = {
					@ApiResponse(code = 201, message = "Successfully created Todo from TodoFrom"),
					@ApiResponse(code = 400, message = "Failed to Create Todo from TodoForm - `title` was missing")
	})
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody TodoForm form) {
		Todo created = todoService.create(form);
		return ResponseEntity.created(URI.create(String.format("/todos/%d", created.getId()))).build();
	}

	@ApiOperation(value = "Update a Todo from a TodoFrom", response = Todo.class)
	@ApiResponses(value = {
					@ApiResponse(code = 200, message = "Successfully created Todo from TodoFrom"),
					@ApiResponse(code = 404, message = "Unable to find Todo to update using provided ID")
	})
	@PutMapping
	public ResponseEntity<Todo> update(@Valid @RequestBody TodoForm form) {
		return ResponseEntity.ok(todoService.update(form));
	}

	@ApiOperation(value = "Mark a Todo as `completed` (Partial Update)", response = Todo.class)
	@ApiResponses(value = {
					@ApiResponse(code = 200, message = "Successfully completed Todo"),
					@ApiResponse(code = 404, message = "Failed to complete Todo using provided ID")
	})
	@PatchMapping("/{id}")
	public ResponseEntity<Todo> complete(@PathVariable("id") Long id) {
		if (id != null && id > 0) {
			return ResponseEntity.ok(todoService.complete(id));
		}
		throw new PathVariableExpectedException(id);
	}

	@ApiOperation(value = "Delete a Todo by ID")
	@ApiResponses(value = {
					@ApiResponse(code = 204, message = "Successfully deleted Todo"),
					@ApiResponse(code = 400, message = "Failed to delete Todo - valid path variable was missing")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (id != null && id > 0) {
			todoService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new PathVariableExpectedException(id);
	}

	@ApiOperation(value = "Truncate all records from Database - Not a standard operation")
	@ApiResponses(value = {
					@ApiResponse(code = 204, message = "Successfully created Todo from TodoFrom")
	})
	@DeleteMapping("/truncate")
	public ResponseEntity<Void> truncate() {
		todoService.truncate();
		return ResponseEntity.noContent().build();
	}
}
