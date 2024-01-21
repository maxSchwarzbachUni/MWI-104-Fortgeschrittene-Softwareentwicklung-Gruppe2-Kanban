package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import java.sql.Date;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.TaskReportId;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.Dto.MessageTaskReportDto;

public class TaskReportFactory {

	public TaskReportEntity ConvertToEntity(TaskReport taskreport) {
		int taskReportId = taskreport.getId().getId();
		return new TaskReportEntity(taskReportId, taskreport.getTaskId(), taskreport.getKanbanboardId(), taskreport.getLastChange(), taskreport.getCreationDate(), taskreport.getClosedDate());
	}	
	
	public TaskReportEntity ConvertToEntityWithoutId(TaskReport taskreport) {
		return new TaskReportEntity(taskreport.getTaskId(), taskreport.getKanbanboardId(), taskreport.getLastChange(), taskreport.getCreationDate(), taskreport.getClosedDate());
	}	
	
	public TaskReport ConvertToAggregate(TaskReportEntity taskReportEntity) {
		TaskReportId taskReportId = new TaskReportId (taskReportEntity.getId());
		return new TaskReport(taskReportId, taskReportEntity.getTaskId(), taskReportEntity.getKanbanboardId(), taskReportEntity.getLastChange(), taskReportEntity.getCreationDate(), taskReportEntity.getClosedDate());
	}

	public TaskReport ConvertToAggregate(MessageTaskReportDto messageTaskReportData) {
		Date closeDate = null;
		if(messageTaskReportData.getLastChange() != null && messageTaskReportData.getUpdateToLastStage()){
			closeDate = messageTaskReportData.getLastChange();			
		}		
		TaskReport result = new TaskReport(messageTaskReportData.getTaskId(), messageTaskReportData.getKanbanid(), messageTaskReportData.getLastChange(), messageTaskReportData.getCreationDate(), closeDate);
		return result;
	}
	
	public TaskReportEntity ConvertToEntityWithoutId(MessageTaskReportDto messageTaskReportData) {
		Date closeDate = null;
		if(messageTaskReportData.getLastChange() != null && messageTaskReportData.getUpdateToLastStage()){
			closeDate = messageTaskReportData.getLastChange();			
		}		
		return new TaskReportEntity(messageTaskReportData.getTaskId(), messageTaskReportData.getKanbanid(), messageTaskReportData.getLastChange(), messageTaskReportData.getCreationDate(), closeDate);
	}	
}
