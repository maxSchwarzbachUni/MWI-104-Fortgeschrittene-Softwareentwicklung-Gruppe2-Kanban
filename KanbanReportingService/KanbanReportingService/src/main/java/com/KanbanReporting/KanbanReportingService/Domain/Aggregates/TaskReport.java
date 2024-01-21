package com.KanbanReporting.KanbanReportingService.Domain.Aggregates;

import java.sql.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.KanbanReporting.KanbanReportingService.Domain.Helper.DateHelper;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;

public class TaskReport {
	TaskReportId id; 
	int taskId;
//	int stageId;
	int kanbanboardId;
	Date lastChange; 
	Date creationDate;
	Date closedDate;
	Map<TimeUnit,Long> LeadTime;
	Map<TimeUnit,Long> UnfinishedProcessingTime;
	Map<TimeUnit,Long> IdleTime;
	
	public TaskReport() {
		super();
	}

	public TaskReport(TaskReportId id, int taskId, int kanbanboardId, Date lastChange, Date creationDate, Date closedDate) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.kanbanboardId = kanbanboardId;
		this.lastChange = lastChange;
		this.creationDate = creationDate;
		this.closedDate = closedDate;
		LeadTime = getLeadTime();
		UnfinishedProcessingTime = getUnfinishedProcessingTime();
		IdleTime = getIdleTime();
	}

	public TaskReport(int taskId, int kanbanboardId, Date lastChange, Date creationDate, Date closedDate) {
		super();
		this.taskId = taskId;
		this.kanbanboardId = kanbanboardId;
		this.lastChange = lastChange;
		this.creationDate = creationDate;
		this.closedDate = closedDate;
		LeadTime = getLeadTime();
		UnfinishedProcessingTime = getUnfinishedProcessingTime();
		IdleTime = getIdleTime();
	}
	
	public TaskReportId getId() {
		return id;
	}
	
	public void setId(TaskReportId id) {
		this.id = id;
	}
	
	public int getTaskId() {
		return taskId;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
//	public int getStageId() {
//		return stageId;
//	}
//	
//	public void setStageId(int stageId) {
//		this.stageId = stageId;
//	}
	
	public int getKanbanboardId() {
		return kanbanboardId;
	}
	
	public void setKanbanboardId(int kanbanboardId) {
		this.kanbanboardId = kanbanboardId;
	}
	
	public Date getLastChange() {
		return lastChange;
	}
	
	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getClosedDate() {
		return closedDate;
	}
	
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	
	public Map<TimeUnit,Long> getLeadTime() {
		if(getClosedDate()!= null) {
			return DateHelper.ComputeDateDifference(creationDate, closedDate);
		}
		return null;
	}
	
	public double getLeadTimeInHours() {
		if(getClosedDate()!= null) {
			return DateHelper.GetDateDifferenceSimple(creationDate, closedDate, TimeUnit.HOURS);
		}
		return -1;
	}
	
	public Map<TimeUnit,Long> getIdleTime() {
		if(getCreationDate()!= null && getClosedDate() == null) {
			return DateHelper.ComputeDateDifference(lastChange, DateHelper.getCurrentTime());
		}
		return null;
	}
	
	public double getIdleTimeInHours() {
		if(getLastChange()!= null && getClosedDate() == null) {
			return DateHelper.GetDateDifferenceSimple(lastChange, DateHelper.getCurrentTime(), TimeUnit.HOURS);
		}
		return -1;
	}
	
	public Map<TimeUnit,Long> getUnfinishedProcessingTime() {
		if(getCreationDate()!= null && getClosedDate() == null) {
			return DateHelper.ComputeDateDifference(creationDate, DateHelper.getCurrentTime());
		}
		return null;
	}
	
	public double getUnfinishedProcessingTimeInHours() {
		if(getCreationDate()!= null && getClosedDate() == null) {
			return DateHelper.GetDateDifferenceSimple(creationDate, DateHelper.getCurrentTime(), TimeUnit.HOURS);
		}
		return -1;
	}
}
