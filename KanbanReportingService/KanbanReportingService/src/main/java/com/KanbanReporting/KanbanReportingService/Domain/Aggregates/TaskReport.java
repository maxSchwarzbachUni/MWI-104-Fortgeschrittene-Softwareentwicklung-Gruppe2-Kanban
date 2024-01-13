package com.KanbanReporting.KanbanReportingService.Domain.Aggregates;

import java.sql.Date;

import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;

public class TaskReport {
	TaskReportId id; 
	int taskId;
	int stageId;
	int kanbanboardId;
	Date lastChange; 
	Date creationDate;
	Date closedDate;
	
	public TaskReport() {
		super();
	}

	public TaskReport(TaskReportId id, int taskId, int stageId, int kanbanboardId, Date lastChange, Date creationDate,
			Date closedDate) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.stageId = stageId;
		this.kanbanboardId = kanbanboardId;
		this.lastChange = lastChange;
		this.creationDate = creationDate;
		this.closedDate = closedDate;
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
	
	public int getStageId() {
		return stageId;
	}
	
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	
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
}
