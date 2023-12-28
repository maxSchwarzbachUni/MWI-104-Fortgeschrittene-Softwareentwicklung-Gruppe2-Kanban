package com.KanbanManagement.KanbanmanagementService.Entities;

import java.sql.Date;

import org.springframework.data.annotation.Id;
// Fix, damit org.springframework.data import möglich ist:
// https://stackoverflow.com/questions/46116947/the-import-org-springframework-data-cannot-be-resolved

public class TaskEntity {
	@Id
	int id;
	String name;
	int assignedstage;
	String description;
	double remainingworkload;
	Date creationdate;
	byte tasktype;
	int lastchange;
	byte priority;
	
	public TaskEntity() {
		
	}
	
	public TaskEntity(int id, String name, int assignedstage, String description, double remainingworkload,
			Date creationdate, byte tasktype, int lastchange, byte priority) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRemainingworkload() {
		return remainingworkload;
	}

	public void setRemainingworkload(double remainingworkload) {
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

	public int getLastchange() {
		return lastchange;
	}

	public void setLastchange(int lastchange) {
		this.lastchange = lastchange;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}
}
