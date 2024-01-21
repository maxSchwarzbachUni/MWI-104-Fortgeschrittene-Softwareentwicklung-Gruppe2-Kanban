package com.KanbanReporting.KanbanReportingService.Domain.Aggregates;

import java.sql.Date;
import java.util.Objects;

import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;

public class KanbanDashboard {
	KanbanDashboardId id;
	int kanbanId;
	double avgLeadTime;
	double avgIdleTime;
	double avgUnfinishedProcessingTimeInHours;
	double avgUnfinishedProcessingTimeInDays;
	int oldestActiveTask;
	double oldestActiveTaskTimeInHours;
	double oldestActiveTaskTimeInDays;

	Date dashboardCreationDate;
		
	public KanbanDashboard() {
		super();
		
	}

	public KanbanDashboard(KanbanDashboardId id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask, double avgUnfinishedProcessingTime) {
		super();
		this.id = id;
		this.kanbanId = kanbanId;
		this.avgLeadTime = avgLeadTime;
		this.avgIdleTime = avgCycleTime;
		this.oldestActiveTask = oldestActiveTask;
		this.avgUnfinishedProcessingTimeInHours = avgUnfinishedProcessingTime;
		this.avgUnfinishedProcessingTimeInDays = avgUnfinishedProcessingTimeInHours / 24;
		this.oldestActiveTaskTimeInDays = oldestActiveTaskTimeInHours / 24;
	}
	
	
	
	public KanbanDashboard(KanbanDashboardId id, int kanbanId, double avgLeadTime, double avgIdleTime,
			double avgUnfinishedProcessingTime, int oldestActiveTask, double oldestActiveTaskTime,
			Date dashboardCreationDate) {
		super();
		this.id = id;
		this.kanbanId = kanbanId;
		this.avgLeadTime = avgLeadTime;
		this.avgIdleTime = avgIdleTime;
		this.avgUnfinishedProcessingTimeInHours = avgUnfinishedProcessingTime;
		this.oldestActiveTask = oldestActiveTask;
		this.oldestActiveTaskTimeInHours = oldestActiveTaskTime;
		this.dashboardCreationDate = dashboardCreationDate;
		this.avgUnfinishedProcessingTimeInDays = avgUnfinishedProcessingTimeInHours / 24;
		this.oldestActiveTaskTimeInDays = oldestActiveTaskTimeInHours / 24;
	}

	public KanbanDashboardId getId() {
		return id;
	}

	public void setId(KanbanDashboardId id) {
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

	public double getAvgIdleTime() {
		return avgIdleTime;
	}

	public void setAvgIdleTime(double avgIdleTime) {
		this.avgIdleTime = avgIdleTime;
	}

	public double getAvgUnfinishedProcessingTimeInHours() {
		return avgUnfinishedProcessingTimeInHours;
	}

	public void setAvgUnfinishedProcessingTimeInHours(double avgUnfinishedProcessingTimeInHours) {
		this.avgUnfinishedProcessingTimeInHours = avgUnfinishedProcessingTimeInHours;
	}

	public double getAvgUnfinishedProcessingTimeInDays() {
		return avgUnfinishedProcessingTimeInDays;
	}

	public void setAvgUnfinishedProcessingTimeInDays(double avgUnfinishedProcessingTimeInDays) {
		this.avgUnfinishedProcessingTimeInDays = avgUnfinishedProcessingTimeInDays;
	}

	public int getOldestActiveTask() {
		return oldestActiveTask;
	}

	public void setOldestActiveTask(int oldestActiveTask) {
		this.oldestActiveTask = oldestActiveTask;
	}

	public double getOldestActiveTaskTimeInHours() {
		return oldestActiveTaskTimeInHours;
	}

	public void setOldestActiveTaskTimeInHours(double oldestActiveTaskTimeInHours) {
		this.oldestActiveTaskTimeInHours = oldestActiveTaskTimeInHours;
	}

	public double getOldestActiveTaskTimeInDays() {
		return oldestActiveTaskTimeInDays;
	}

	public void setOldestActiveTaskTimeInDays(double oldestActiveTaskTimeInDays) {
		this.oldestActiveTaskTimeInDays = oldestActiveTaskTimeInDays;
	}

	public Date getDashboardCreationDate() {
		return dashboardCreationDate;
	}

	public void setDashboardCreationDate(Date dashboardCreationDate) {
		this.dashboardCreationDate = dashboardCreationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avgIdleTime, avgLeadTime, id, kanbanId, oldestActiveTask);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KanbanDashboard other = (KanbanDashboard) obj;
		return Double.doubleToLongBits(avgIdleTime) == Double.doubleToLongBits(other.avgIdleTime)
				&& Double.doubleToLongBits(avgLeadTime) == Double.doubleToLongBits(other.avgLeadTime) && id == other.id
				&& kanbanId == other.kanbanId && oldestActiveTask == other.oldestActiveTask;
	}
}
