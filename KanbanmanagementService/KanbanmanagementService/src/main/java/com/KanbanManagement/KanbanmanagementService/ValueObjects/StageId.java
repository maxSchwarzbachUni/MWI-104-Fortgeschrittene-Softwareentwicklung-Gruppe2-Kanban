package com.KanbanManagement.KanbanmanagementService.ValueObjects;

public class StageId {
int id;

	public StageId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		StageId a = (StageId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
