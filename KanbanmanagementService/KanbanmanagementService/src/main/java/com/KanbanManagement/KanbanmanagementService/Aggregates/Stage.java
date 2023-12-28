package com.KanbanManagement.KanbanmanagementService.Aggregates;

import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;

public class Stage {
	StageId stageId;
	String name;
	int position;
	
	public Stage(StageId stageId, String name, int position) {
		super();
		this.stageId = stageId;
		this.name = name;
		this.position = position;
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
	
	public boolean equals (Object o) {
		Stage a = (Stage)o;
		return (this.stageId == a.stageId);
	}
	
	public int hashCode() {
		return stageId.getId(); 
	}
}
