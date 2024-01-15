package com.KanbanManagement.KanbanmanagementService.Domain.DomainServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskNotificationEmitterService;

public class TaskmanagementDomainService {
	
	private TaskNotificationEmitterService taskChangedNotificationEmitterService;
	
	public TaskmanagementDomainService(TaskNotificationEmitterService taskChangedNotificationEmitterService) {
		this.taskChangedNotificationEmitterService = taskChangedNotificationEmitterService;
	}
		
	public Boolean checkIfTaskEntityIsEmpty(TaskEntity taskToUpdate) {
		return taskToUpdate == null;
	}
	
	public TaskEntity updateAssignedStage(TaskEntity taskToUpdate, int stageId) {
		taskToUpdate.setAssignedstage(stageId);
		return taskToUpdate;
	}
	
	public String createAndSendTaskUpdateNotification(TaskEntity taskToUpdate, int kanbanid) {
		try {
			TaskReportData messageTaskObject = new TaskReportData(new TaskId(taskToUpdate.getId()), taskToUpdate.getCreationdate(), taskToUpdate.getLastchangeDate(), kanbanid);			
			taskChangedNotificationEmitterService.EmitTaskChangedNotificationRabbitMq(messageTaskObject);
			return "Nachrichten verschicken hat erfolgreich geklappt";
		} 
		catch (Exception e) {
			return "Fehler bei Update von Task! Meldung: /n" + e.getMessage();
		}
	}
}
