package com.KanbanManagement.KanbanmanagementService.Domain.Aggregates;

import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

public class Stage {
	StageId stageId;
	String name;
	int position;
	int kanbanid;
	
	public Stage(StageId stageId, String name, int position, int kanbanid) {
		super();
		this.stageId = stageId;
		this.name = name;
		this.position = position;
		this.kanbanid = kanbanid;
	}
	
	public StageId getId() {
		return stageId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
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

	public boolean equals (Object o) {
		Stage a = (Stage)o;
		return (this.stageId == a.stageId);
	}
	
	public int hashCode() {
		return stageId.getId(); 
	}
}
