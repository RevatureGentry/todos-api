package com.revature.todo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author William Gentry
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
