package com.KanbanManagement.KanbanmanagementService.Domain.DomainServices;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.TaskFactory;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskManagementKonstanten;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskNotificationEmitterService;

public class TaskmanagementDomainService {
	
	private TaskNotificationEmitterService taskChangedNotificationEmitterService;
	private TaskFactory taskFactory;
	
	public TaskmanagementDomainService(TaskNotificationEmitterService taskChangedNotificationEmitterService, TaskFactory taskFactory) {
		this.taskChangedNotificationEmitterService = taskChangedNotificationEmitterService;
		this.taskFactory = taskFactory; 
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

	public ResponseEntity<Object> ConvertTaskListToTaskEntityList(Iterable<TaskEntity> taskEntityList) {
		return taskFactory.ConvertTaskListToTaskEntityList(taskEntityList);
	}

	public Task ConvertTaskToAggregate(TaskEntity TaskEntity) {
		return taskFactory.ConvertToAggregate(TaskEntity);
	}

	public DomainResult checkTaskAndStageEntities(TaskEntity taskToUpdate, StageEntity stageEntity) {
		Boolean entityIsEmpty = checkIfTaskEntityIsEmpty(taskToUpdate);
		if(entityIsEmpty) {
			return new DomainResult(false, TaskManagementKonstanten.task_update_failed_no_task_for_update_found, HttpStatus.NOT_FOUND);
		}
		
		if(stageEntity == null) {
			return new DomainResult(false, TaskManagementKonstanten.task_update_failed_stage_to_update_to_not_found, HttpStatus.NOT_FOUND);
		}
		return new DomainResult(true, null, HttpStatus.OK);	
	}
}
