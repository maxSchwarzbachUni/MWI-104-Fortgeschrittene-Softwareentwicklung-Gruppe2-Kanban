package com.KanbanReporting.KanbanReportingService.Domain.Entities;

import java.sql.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table (name = "TASKREPORT")
public class TaskReportEntity {
	@Id
	int id; 
	int taskid;
//	int stageId;
	int kanbanboardid;
	Date lastchange; 
	Date creationdate;
	Date closeddate;
	
	public TaskReportEntity() {
		super();
	}
	
	public TaskReportEntity(int taskId, int stageId, int kanbanboardId, Date lastChange, Date creationDate, Date closedDate) {
		super();
		this.taskid = taskId;
//		this.stageId = stageId;
		this.kanbanboardid = kanbanboardId;
		this.lastchange = lastChange;
		this.creationdate = creationDate;
		this.closeddate = closedDate;
	}

	public TaskReportEntity(int id, int taskId, int stageId, int kanbanboardId, Date lastChange, Date creationDate, Date closedDate) {
		super();
		this.id = id;
		this.taskid = taskId;
//		this.stageId = stageId;
		this.kanbanboardid = kanbanboardId;
		this.lastchange = lastChange;
		this.creationdate = creationDate;
		this.closeddate = closedDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTaskId() {
		return taskid;
	}
	
	public void setTaskId(int taskId) {
		this.taskid = taskId;
	}
	
//	public int getStageId() {
//		return stageId;
//	}
//	
//	public void setStageId(int stageId) {
//		this.stageId = stageId;
//	}
	
	public int getKanbanboardId() {
		return kanbanboardid;
	}
	
	public void setKanbanboardId(int kanbanboardId) {
		this.kanbanboardid = kanbanboardId;
	}
	
	public Date getLastChange() {
		return lastchange;
	}
	
	public void setLastChange(Date lastChange) {
		this.lastchange = lastChange;
	}
	
	public Date getCreationDate() {
		return creationdate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationdate = creationDate;
	}
	
	public Date getClosedDate() {
		return closeddate;
	}
	
	public void setClosedDate(Date closedDate) {
		this.closeddate = closedDate;
	}
}
