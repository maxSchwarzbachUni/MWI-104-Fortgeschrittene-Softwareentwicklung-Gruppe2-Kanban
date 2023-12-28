package com.KanbanManagement.KanbanmanagementService.ValueObjects;

public class KanbanId {
int id;

	public KanbanId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		KanbanId a = (KanbanId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}