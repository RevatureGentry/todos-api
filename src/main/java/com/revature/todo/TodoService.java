package com.revature.todo;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author William Gentry
 */
@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> findAllTodos() {
		return todoRepository.findAll();
	}

	public Todo findById(long id) {
		return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
	}

	public Todo create(TodoForm form) {
		return todoRepository.save(new Todo(form));
	}

	public Todo update(TodoForm form) {
		Todo found = todoRepository.findById(form.getId()).orElseThrow(() -> new TodoNotFoundException(form.getId()));
		found.setTitle(form.getTitle());
		found.setCompleted(form.isCompleted());
		return todoRepository.save(found);
	}

	public void delete(long id) {
		todoRepository.findById(id).ifPresent(todoRepository::delete);
	}

	public Todo complete(long id) {
		Todo found = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
		found.setCompleted(true);
		return todoRepository.save(found);
	}

	public void truncate() {
		todoRepository.deleteAll();
	}
}
