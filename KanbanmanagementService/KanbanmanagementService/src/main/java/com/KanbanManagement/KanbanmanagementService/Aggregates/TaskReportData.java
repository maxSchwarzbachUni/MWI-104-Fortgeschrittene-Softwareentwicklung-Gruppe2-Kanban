package com.KanbanManagement.KanbanmanagementService.Aggregates;

import java.sql.Date;

import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskReportData {
	TaskId taskId;
    Date creationDate;
    Date lastChange;
    int kanbanid;    
    
    public TaskReportData(TaskId taskId, Date creationDate, Date lastChange, int kanbanid) {
		super();
		this.taskId = taskId;
		this.creationDate = creationDate;
		this.lastChange = lastChange;
		this.kanbanid = kanbanid;
	}
    
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

	public int getKanbanid() {
		return kanbanid;
	}

	public void setKanbanid(int kanbanid) {
		this.kanbanid = kanbanid;
	}       
}
