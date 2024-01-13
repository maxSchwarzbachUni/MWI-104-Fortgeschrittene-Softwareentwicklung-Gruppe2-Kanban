package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import java.sql.Date;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;
import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;

public class TaskReportFactory {

	public TaskReportEntity ConvertToEntity(TaskReport kanbanDashboard) {
		int taskReportId = kanbanDashboard.getId().getId();
		return new TaskReportEntity(taskReportId, kanbanDashboard.getTaskId(), kanbanDashboard.getStageId(), kanbanDashboard.getKanbanboardId(), kanbanDashboard.getLastChange(), kanbanDashboard.getCreationDate(), kanbanDashboard.getClosedDate());
	}
	
//	public TaskReport ConvertToAggregate(TaskReportEntity taskReportEntity) {
//		TaskReportId taskReportId = new TaskReportId (taskReportEntity.getId());
//		double avgLeadTime = taskReportEntity.getav
//		
//		return new KanbanDashboard(taskReportId, taskReportEntity.getKanbanboardId(), taskReportEntity.get);	
//		KanbanDashboardId id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask
//	}
}
