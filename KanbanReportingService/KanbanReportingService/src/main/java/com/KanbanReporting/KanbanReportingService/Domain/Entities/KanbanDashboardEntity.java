package com.KanbanReporting.KanbanReportingService.Domain.Entities;

public class KanbanDashboardEntity {
	
	int id;
	int kanbanId;
	double avgLeadTime;
	double avgCycleTime;
	int oldestActiveTask;
	
	public KanbanDashboardEntity() {
		super();
	}

	public KanbanDashboardEntity(int id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask) {
		super();
		this.id = id;
		this.kanbanId = kanbanId;
		this.avgLeadTime = avgLeadTime;
		this.avgCycleTime = avgCycleTime;
		this.oldestActiveTask = oldestActiveTask;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getKanbanId() {
		return kanbanId;
	}
	
	public void setKanbanId(int kanbanId) {
		this.kanbanId = kanbanId;
	}
	
	public double getAvgLeadTime() {
		return avgLeadTime;
	}
	
	public void setAvgLeadTime(double avgLeadTime) {
		this.avgLeadTime = avgLeadTime;
	}
	
	public double getAvgCycleTime() {
		return avgCycleTime;
	}
	
	public void setAvgCycleTime(double avgCycleTime) {
		this.avgCycleTime = avgCycleTime;
	}
	
	public int getOldestActiveTask() {
		return oldestActiveTask;
	}
	
	public void setOldestActiveTask(int oldestActiveTask) {
		this.oldestActiveTask = oldestActiveTask;
	}
}
