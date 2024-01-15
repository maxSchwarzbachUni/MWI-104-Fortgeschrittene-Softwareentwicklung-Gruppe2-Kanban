package com.KanbanManagement.KanbanmanagementService.Domain.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table (name = "STAGE")
public class StageEntity {
	@Id
	int id;
	String name;
	int position;
	int kanbanid;
	
	public StageEntity() {

	}
	
	public StageEntity(int id, String name, int position) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
	}
	
	public StageEntity(String name, int position) {
		super();
		this.name = name;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStageName() {
		return name;
	}

	public void setStageName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getKanbanid() {
		return kanbanid;
	}

	public void setKanbanid(int kanbanid) {
		this.kanbanid = kanbanid;
	}
}