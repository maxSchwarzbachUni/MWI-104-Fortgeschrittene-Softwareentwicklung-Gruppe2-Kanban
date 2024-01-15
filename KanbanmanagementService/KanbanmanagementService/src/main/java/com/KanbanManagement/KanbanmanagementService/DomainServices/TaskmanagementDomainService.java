package com.KanbanManagement.KanbanmanagementService.DomainServices;

import com.KanbanManagement.KanbanmanagementService.Aggregates.TaskReportData;
import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskChangedNotificationEmitterService;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskmanagementDomainService {
	
	private TaskChangedNotificationEmitterService taskChangedNotificationEmitterService;
	
	public TaskmanagementDomainService(TaskChangedNotificationEmitterService taskChangedNotificationEmitterService) {
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
