package com.revature.todo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
@JacksonXmlRootElement
@Entity
public class Todo {

	@Id
	@GeneratedValue
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Unique Identifier for Todo")
	private long id;

	@Column(name = "title")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "The purpose of the Todo - what you need to do")
	private String title;

	@CreationTimestamp
	@Column(name = "created_on")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Automatically set at persistence")
	private LocalDate createdOn;

	@Column(name = "completed")
	@JacksonXmlProperty
	@ApiModelProperty(notes = "Whether the Todo has been completed")
	private boolean completed;

	public Todo() {}

	public Todo(TodoForm form) {
		this.id = form.getId();
		this.title = form.getTitle();
	}

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

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
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
		Todo todo = (Todo) o;
		return id == todo.id &&
						completed == todo.completed &&
						Objects.equals(title, todo.title) &&
						Objects.equals(createdOn, todo.createdOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, createdOn, completed);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Todo.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("title='" + title + "'")
						.add("createdOn=" + createdOn)
						.add("completed=" + completed)
						.toString();
	}
}
