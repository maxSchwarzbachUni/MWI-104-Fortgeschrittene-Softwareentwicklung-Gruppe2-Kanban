package com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices;

import java.sql.Date;

public class MessageTaskReportDto {
	int taskId;
	Date creationDate;
	Date lastChange;
	int kanbanid;
	Boolean updateToLastStage;
	
	
	public MessageTaskReportDto() {
		super();
	}
	
	public MessageTaskReportDto(int taskId, Date creationDate, Date lastChange, int kanbanid,
			Boolean updateToLastStage) {
		super();
		this.taskId = taskId;
		this.creationDate = creationDate;
		this.lastChange = lastChange;
		this.kanbanid = kanbanid;
		this.updateToLastStage = updateToLastStage;
	}
	
	public int getTaskId() {
		return taskId;
	}
	
	public void setTaskId(int taskId) {
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
	
	public Boolean getUpdateToLastStage() {
		return updateToLastStage;
	}
	
	public void setUpdateToLastStage(Boolean updateToLastStage) {
		this.updateToLastStage = updateToLastStage;
	}		
}
