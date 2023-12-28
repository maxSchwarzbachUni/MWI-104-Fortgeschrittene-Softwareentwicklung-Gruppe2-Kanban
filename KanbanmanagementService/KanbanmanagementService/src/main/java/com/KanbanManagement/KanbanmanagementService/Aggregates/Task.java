package com.KanbanManagement.KanbanmanagementService.Aggregates;

import java.sql.Date;

import com.KanbanManagement.KanbanmanagementService.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class Task {
	TaskId taskId;
	String name;
	StageId assignedstage;
	String description;
	double remainingworkload;
	Date creationdate;
	TaskType tasktype;
	Date lastchange;
	byte priority;
	
	public Task(TaskId taskId, String name, StageId assignedstage, String description, double remainingworkload,
			Date creationdate, TaskType tasktype, Date lastchange, byte priority) {
		super();
		this.taskId = taskId;
		this.name = name;
		this.assignedstage = assignedstage;
		this.description = description;
		this.remainingworkload = remainingworkload;
		this.creationdate = creationdate;
		this.tasktype = tasktype;
		this.lastchange = lastchange;
		this.priority = priority;
	}
	
	public TaskId getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return name;
	}

	public void setTaskName(String name) {
		this.name = name;
	}

	public StageId getAssignedstage() {
		return assignedstage;
	}

	public void setAssignedstage(StageId assignedstage) {
		this.assignedstage = assignedstage;
	}

	public String getTaskDescription() {
		return description;
	}

	public void setTaskDescription(String description) {
		this.description = description;
	}

	public double getRemainingworkload() {
		return remainingworkload;
	}

	public void adjustRemainingworkload(double remainingworkload) {
		this.remainingworkload = remainingworkload;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public TaskType getTasktype() {
		return tasktype;
	}

	public void setTasktype(TaskType tasktype) {
		this.tasktype = tasktype;
	}

	public Date getLastchangeDate() {
		return lastchange;
	}

	public void setLastchangeDate(Date lastchange) {
		this.lastchange = lastchange;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public boolean equals (Object o) {
		Task a = (Task)o;
		return (this.taskId == a.taskId);
	}
	
	public int hashCode() {
		return taskId.getId(); 
	}
}
