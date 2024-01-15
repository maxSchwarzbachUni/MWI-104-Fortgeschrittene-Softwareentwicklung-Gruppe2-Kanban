package com.KanbanManagement.KanbanmanagementService.Domain.Aggregates;

import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.KanbanId;

public class Kanbanboard {
	KanbanId kanbanId;
	String name;
	
	public Kanbanboard(KanbanId kanbanId, String name) {
		super();
		this.kanbanId = kanbanId;
		this.name = name;
	}
	
	public KanbanId getKanbanId() {
		return kanbanId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals (Object o) {
		Kanbanboard a = (Kanbanboard)o;
		return (this.kanbanId == a.kanbanId);
	}
	
	public int hashCode() {
		return kanbanId.getId(); 
	}
}
