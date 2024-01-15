package com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects;

public class TaskId {
int id;

	public TaskId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		TaskId a = (TaskId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
