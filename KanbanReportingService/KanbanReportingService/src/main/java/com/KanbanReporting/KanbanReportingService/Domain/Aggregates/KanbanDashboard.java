package com.KanbanReporting.KanbanReportingService.Domain.Aggregates;

import java.util.Objects;

import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;

public class KanbanDashboard {
	KanbanDashboardId id;
	int kanbanId;
	double avgLeadTime;
	double avgCycleTime;
	int oldestActiveTask;
		
	public KanbanDashboard() {
		super();
	}

	public KanbanDashboard(KanbanDashboardId id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask) {
		super();
		this.id = id;
		this.kanbanId = kanbanId;
		this.avgLeadTime = avgLeadTime;
		this.avgCycleTime = avgCycleTime;
		this.oldestActiveTask = oldestActiveTask;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(avgCycleTime, avgLeadTime, id, kanbanId, oldestActiveTask);
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
		return Double.doubleToLongBits(avgCycleTime) == Double.doubleToLongBits(other.avgCycleTime)
				&& Double.doubleToLongBits(avgLeadTime) == Double.doubleToLongBits(other.avgLeadTime) && id == other.id
				&& kanbanId == other.kanbanId && oldestActiveTask == other.oldestActiveTask;
	}
}
