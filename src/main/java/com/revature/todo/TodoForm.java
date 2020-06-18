package com.revature.todo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class TodoForm {

	@ApiModelProperty(notes = "(Optional) The ID of the Todo to manipulate")
	private long id;

	/**
	 *  What the actual task you need to do is
	 */
	@ApiModelProperty(notes = "Desired `title` of the Todo")
	@NotBlank(message = "Title must not be blank")
	private String title;

	@ApiModelProperty(notes = "Desired `completed` of the Todo")
	private boolean completed;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TodoForm todoForm = (TodoForm) o;
		return id == todoForm.id &&
						completed == todoForm.completed &&
						Objects.equals(title, todoForm.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, completed);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", TodoForm.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("title='" + title + "'")
						.add("complete=" + completed)
						.toString();
	}
}
