package com.KanbanReporting.KanbanReportingService.Domain.ValueObjects;

public class KanbanDashboardId {
	int id;

	public KanbanDashboardId(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals (Object o) {
		KanbanDashboardId a = (KanbanDashboardId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
