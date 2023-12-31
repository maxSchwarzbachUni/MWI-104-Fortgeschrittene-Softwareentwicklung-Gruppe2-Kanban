package com.KanbanManagement.KanbanmanagementService.Entities;

import java.sql.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
// Fix, damit org.springframework.data import möglich ist:
// https://stackoverflow.com/questions/46116947/the-import-org-springframework-data-cannot-be-resolved

@Table (name = "TASK")
public class TaskEntity {
	@Id
	int id;
	String name;
	int assignedstage;
	String description;
	double remainingworkload;
	Date creationdate;
	byte tasktype;
	Date lastchange;
	byte priority;
	
	public TaskEntity() {
		
	}
	
	public TaskEntity(int id, String name, int assignedstage, String description, double remainingworkload,
			Date creationdate, byte tasktype, Date lastchange, byte priority) {
		super();
		this.id = id;
		this.name = name;
		this.assignedstage = assignedstage;
		this.description = description;
		this.remainingworkload = remainingworkload;
		this.creationdate = creationdate;
		this.tasktype = tasktype;
		this.lastchange = lastchange;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAssignedstage() {
		return assignedstage;
	}

	public void setAssignedstage(int assignedstage) {
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

	public byte getTasktype() {
		return tasktype;
	}

	public void setTasktype(byte tasktype) {
		this.tasktype = tasktype;
	}

	public Date getLastchangeDate() {
		return lastchange;
	}

	public void setLastchangeDate(Date lastchange) {
		this.lastchange = lastchange;
	}

	public byte getTaskPriority() {
		return priority;
	}

	public void setTaskPriority(byte priority) {
		this.priority = priority;
	}
}
