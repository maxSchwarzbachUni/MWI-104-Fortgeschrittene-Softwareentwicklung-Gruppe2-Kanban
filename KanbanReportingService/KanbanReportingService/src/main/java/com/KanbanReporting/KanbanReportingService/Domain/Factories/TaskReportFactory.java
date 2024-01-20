package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;

public class TaskReportFactory {

	public TaskReportEntity ConvertToEntity(TaskReport kanbanDashboard) {
		int taskReportId = kanbanDashboard.getId().getId();
		return new TaskReportEntity(taskReportId, kanbanDashboard.getTaskId(), kanbanDashboard.getStageId(), kanbanDashboard.getKanbanboardId(), kanbanDashboard.getLastChange(), kanbanDashboard.getCreationDate(), kanbanDashboard.getClosedDate());
	}
	
	
//	public TaskReportEntity ConvertFromDtoToEntity(MessageTaskReportDto dto) {
//		
////		int taskId, int stageId, int kanbanboardId, Date lastChange, Date creationDate, Date closedDate
//		return new TaskReportEntity(dto.getTaskId(), dto.)
//		
//	}
	
//	
	public TaskReport ConvertToAggregate(TaskReportEntity taskReportEntity) {
		TaskReportId taskReportId = new TaskReportId (taskReportEntity.getId());
		return new TaskReport(taskReportId, taskReportEntity.getTaskId(), taskReportEntity.getKanbanboardId(), taskReportEntity.getLastChange(), taskReportEntity.getCreationDate(), taskReportEntity.getClosedDate());
	}
}
