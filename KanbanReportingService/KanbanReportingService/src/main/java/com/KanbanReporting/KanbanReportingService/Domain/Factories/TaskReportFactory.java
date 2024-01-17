package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import java.sql.Date;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.MessageTaskReportDto;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.MessageTaskReportDto;
import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;

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
//	public TaskReport ConvertToAggregate(TaskReportEntity taskReportEntity) {
//		TaskReportId taskReportId = new TaskReportId (taskReportEntity.getId());
//		double avgLeadTime = taskReportEntity.getav
//		
//		return new KanbanDashboard(taskReportId, taskReportEntity.getKanbanboardId(), taskReportEntity.get);	
//		KanbanDashboardId id, int kanbanId, double avgLeadTime, double avgCycleTime, int oldestActiveTask
//	}
}
