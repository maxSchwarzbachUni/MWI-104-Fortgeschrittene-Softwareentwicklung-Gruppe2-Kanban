package com.KanbanManagement.KanbanmanagementService.Domain.Entities;

import java.sql.Date;

import org.springframework.data.annotation.Id;



public class KanbanboardEntity {
	@Id
	int id;
	String name;
	private Date DashboardCreationDate;
	
	public KanbanboardEntity() {

	}
		
	public KanbanboardEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKanbanboardName() {
		return name;
	}

	public void setKanbanboardName(String name) {
		this.name = name;
	}

	public Date getDashboardCreationDate() {
		return DashboardCreationDate;
	}

	public void setDashboardCreationDate(Date dashboardCreationDate) {
		DashboardCreationDate = dashboardCreationDate;
	}
}