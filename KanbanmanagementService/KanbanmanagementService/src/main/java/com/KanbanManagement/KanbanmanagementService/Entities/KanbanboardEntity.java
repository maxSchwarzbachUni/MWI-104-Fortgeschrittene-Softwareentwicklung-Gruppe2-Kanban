package com.KanbanManagement.KanbanmanagementService.Entities;

import org.springframework.data.annotation.Id;

public class KanbanboardEntity {
	@Id
	int id;
	String name;
	
	public KanbanboardEntity() {

	}
		
	public KanbanboardEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKanbanboardName() {
		return name;
	}

	public void setKanbanboardName(String name) {
		this.name = name;
	}
}