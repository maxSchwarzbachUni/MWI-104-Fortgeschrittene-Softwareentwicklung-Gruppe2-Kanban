package com.KanbanReporting.KanbanReportingService.Domain.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table (name = "KANBANDASHBOARD")
public class KanbanDashboardEntity {
	@Id
	int id;
	int kanbanid;
	double avgleadtime;
	double avgcycletime;
	int oldestactivetask;
	
	public KanbanDashboardEntity() {
		super();
	}

	public KanbanDashboardEntity(int id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask) {
		super();
		this.id = id;
		this.kanbanid = kanbanId;
		this.avgleadtime = avgLeadTime;
		this.avgcycletime = avgCycleTime;
		this.oldestactivetask = oldestActiveTask;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getKanbanId() {
		return kanbanid;
	}
	
	public void setKanbanId(int kanbanId) {
		this.kanbanid = kanbanId;
	}
	
	public double getAvgLeadTime() {
		return avgleadtime;
	}
	
	public void setAvgLeadTime(double avgLeadTime) {
		this.avgleadtime = avgLeadTime;
	}
	
	public double getAvgCycleTime() {
		return avgcycletime;
	}
	
	public void setAvgCycleTime(double avgCycleTime) {
		this.avgcycletime = avgCycleTime;
	}
	
	public int getOldestActiveTask() {
		return oldestactivetask;
	}
	
	public void setOldestActiveTask(int oldestActiveTask) {
		this.oldestactivetask = oldestActiveTask;
	}
}
