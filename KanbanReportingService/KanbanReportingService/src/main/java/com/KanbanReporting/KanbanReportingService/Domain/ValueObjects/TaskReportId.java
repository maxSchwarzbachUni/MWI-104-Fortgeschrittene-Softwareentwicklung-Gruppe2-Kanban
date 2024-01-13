package com.KanbanReporting.KanbanReportingService.Domain.ValueObjects;

public class TaskReportId {
	int id;

	public TaskReportId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		TaskReportId a = (TaskReportId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
