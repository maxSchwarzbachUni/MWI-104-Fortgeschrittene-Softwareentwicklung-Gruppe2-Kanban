package com.KanbanManagement.KanbanmanagementService.Aggregates;

import java.sql.Date;

import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskReportData {
	public TaskId taskId;
    public Date creationDate;
    public Date lastChange;
    
	public TaskId getTaskId() {
		return taskId;
	}
	
	public void setTaskId(TaskId taskId) {
		this.taskId = taskId;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getLastChange() {
		return lastChange;
	}
	
	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}
    
    
}
