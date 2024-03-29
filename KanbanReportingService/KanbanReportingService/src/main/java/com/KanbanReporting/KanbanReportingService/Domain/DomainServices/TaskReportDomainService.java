package com.KanbanReporting.KanbanReportingService.Domain.DomainServices;

import java.util.ArrayList;
import java.util.List;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;
import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.KanbanDashboardFactory;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.TaskReportFactory;

public class TaskReportDomainService {

	KanbanDashboardFactory kanbanDashboardFactory;
	TaskReportFactory taskReportFactory;
	
	public TaskReportDomainService(TaskReportFactory taskReportFactory, KanbanDashboardFactory kanbanDashboardFactory) {
		this.taskReportFactory = taskReportFactory;
		this.kanbanDashboardFactory = kanbanDashboardFactory;
	}
	
	public TaskReport convertTaskReportEntityToAggregate(TaskReportEntity taskReportEntity) {
		return taskReportFactory.ConvertToAggregate(taskReportEntity);
	}

	public void checkIdForValidity(int id) throws IllegalArgumentException {
		if(id <= 0) {
			throw new IllegalArgumentException("id is less or equal to 0");
		}				
	}

	public List<TaskReport> convertTaskReportEntityListToAggregate(Iterable<TaskReportEntity> taskReportEntities) {
		List<TaskReport> resultList = new ArrayList<TaskReport>();
		for (TaskReportEntity taskReportEntity : taskReportEntities) {
			resultList.add(taskReportFactory.ConvertToAggregate(taskReportEntity));
		}
		return resultList;
	}

	public KanbanDashboard convertKanbanDashboardEntityToAggregate(KanbanDashboardEntity kanbanDashboardEntity) {
		return kanbanDashboardFactory.ConvertToAggregate(kanbanDashboardEntity);
	}

	public List<KanbanDashboard> convertKanbanDashboardEntityListToAggregate(List<KanbanDashboardEntity> kanbanDashboardEntityList) {
		List<KanbanDashboard> resultList = new ArrayList<KanbanDashboard>();
		for (KanbanDashboardEntity kanbanDashboardEntity : kanbanDashboardEntityList) {
			resultList.add(kanbanDashboardFactory.ConvertToAggregate(kanbanDashboardEntity));
		}
		return resultList;
	}
}
