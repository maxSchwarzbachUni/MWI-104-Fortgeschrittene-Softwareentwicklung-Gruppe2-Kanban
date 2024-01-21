package com.KanbanReporting.KanbanReportingService.Domain.Entities;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;

@Table (name = "KANBANDASHBOARD")
public class KanbanDashboardEntity {
	@Id
	int id;
	int kanbanid;
	double avgleadtime;
	double avgidletime;
	double avgunfinishedprocessingtime;
	int oldestactivetask;
	double oldestactivetasktime;
	Date dashboardcreationdate;
	
	public KanbanDashboardEntity() {
		super();
	}

	public KanbanDashboardEntity(int id, int kanbanid, double avgleadtime, double avgIdleTime,
			double avgUnfinishedProcessingTime, int oldestactivetask, double oldestActiveTaskTime,
			Date dashboardCreationDate) {
		super();
		this.id = id;
		this.kanbanid = kanbanid;
		this.avgleadtime = avgleadtime;
		this.avgidletime = avgIdleTime;
		this.avgunfinishedprocessingtime = avgUnfinishedProcessingTime;
		this.oldestactivetask = oldestactivetask;
		this.oldestactivetasktime = oldestActiveTaskTime;
		dashboardcreationdate = dashboardCreationDate;
	}

	public KanbanDashboardEntity(int kanbanid, double avgleadtime, double avgIdleTime,
			double avgUnfinishedProcessingTime, int oldestactivetask, double oldestActiveTaskTime,
			Date dashboardCreationDate) {
		super();
		this.kanbanid = kanbanid;
		this.avgleadtime = avgleadtime;
		this.avgidletime = avgIdleTime;
		this.avgunfinishedprocessingtime = avgUnfinishedProcessingTime;
		this.oldestactivetask = oldestactivetask;
		this.oldestactivetasktime = oldestActiveTaskTime;
		dashboardcreationdate = dashboardCreationDate;
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
	
	public int getOldestActiveTask() {
		return oldestactivetask;
	}
	
	public void setOldestActiveTask(int oldestActiveTask) {
		this.oldestactivetask = oldestActiveTask;
	}
	
	public double getAvgIdleTime() {
		return avgidletime;
	}

	public void setAvgIdleTime(double avgIdleTime) {
		this.avgidletime = avgIdleTime;
	}

	public double getAvgUnfinishedProcessingTime() {
		return avgunfinishedprocessingtime;
	}

	public void setAvgUnfinishedProcessingTime(double avgUnfinishedProcessingTime) {
		this.avgunfinishedprocessingtime = avgUnfinishedProcessingTime;
	}

	public double getOldestActiveTaskTime() {
		return oldestactivetasktime;
	}

	public void setOldestActiveTaskTime(double oldestActiveTaskTime) {
		this.oldestactivetasktime = oldestActiveTaskTime;
	}

	public Date getDashboardCreationDate() {
		return dashboardcreationdate;
	}

	public void setDashboardCreationDate(Date dashboardCreationDate) {
		dashboardcreationdate = dashboardCreationDate;
	}
}
