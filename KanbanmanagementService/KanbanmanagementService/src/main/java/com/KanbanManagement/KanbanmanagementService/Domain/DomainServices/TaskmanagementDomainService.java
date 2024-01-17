package com.KanbanManagement.KanbanmanagementService.Domain.DomainServices;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.TaskFactory;
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
	
	public String createAndSendTaskUpdateNotification(TaskEntity taskToUpdate, int kanbanid, Boolean updateToLastStage) {
		try {
			Date lastchangeDate = taskToUpdate.getLastchangeDate();
			Date creationdate = taskToUpdate.getCreationdate();
			TaskId taskId = new TaskId(taskToUpdate.getId());
			TaskReportData messageTaskObject = new TaskReportData(taskId.getId(), creationdate, lastchangeDate, kanbanid, updateToLastStage);			
			taskChangedNotificationEmitterService.EmitTaskChangedNotificationRabbitMq(messageTaskObject);
		} 
		catch (Exception e) {
			return "Fehler bei Update von Task! Meldung: /n" + e.getMessage();
		}
		return "Nachrichten verschicken hat erfolgreich geklappt";
	}
		
	public Boolean checkIfTaskEntityIsEmpty(TaskEntity taskToUpdate) {
		return taskToUpdate == null;
	}
	
	public Date getCurrentTime() {
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		return java.sql.Date.valueOf(timeNow.toLocalDate());
	}
	
	public TaskEntity updateAssignedStage(TaskEntity taskToUpdate, int stageId) {
		taskToUpdate.setAssignedstage(stageId);
		taskToUpdate.setLastchangeDate(getCurrentTime());
		return taskToUpdate;
	}

	public Object[] ConvertTaskListToTaskEntityList(Iterable<TaskEntity> taskEntityList) {
		return taskFactory.ConvertTaskListToTaskEntityList(taskEntityList).toArray();
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

	public DomainResult validateNewTaskData(Task taskToCreate) {
		Boolean descriptionIsValid = descriptionIsValid(taskToCreate.getTaskDescription());
		Boolean workloadIsValid = workloadIsValid(taskToCreate.getRemainingworkload());
		
		if(descriptionIsValid && workloadIsValid){
			return new DomainResult(true, null, HttpStatus.OK);
		}
		else if(!descriptionIsValid) {
			return new DomainResult(false, "Description Feld ist nicht gültig, da zu lang (Limit 255 Zeichen", HttpStatus.BAD_REQUEST);
		}
		else {
			return new DomainResult(false, "RemainingWorkload Feld ist keine gültige Zahl (sie muss größer 0 sein)", HttpStatus.BAD_REQUEST);
		}		
	}
	
	private Boolean descriptionIsValid(String description) {
		return description.length() < 255;		
	}
	
	private Boolean workloadIsValid(double workload) {
		return workload > 0;
	}

	public Task AmendNewTask(Task taskToAmend) {
		if(taskToAmend.getLastchangeDate() == null) {
			taskToAmend.setLastchangeDate(getCurrentTime());
		}
		if(taskToAmend.getCreationdate() == null) {
			taskToAmend.setCreationdate(getCurrentTime());;
		}	
		
		return taskToAmend;
	}

	public TaskEntity getTaskEntityFromAggrate(Task taskToSave) {
		return taskFactory.ConvertToEntity(taskToSave);
	}

	public TaskEntity getUpdatedTaskEntityWithFieldChanged(TaskEntity taskToUpdate, String fieldname, Object value) {
		switch (fieldname) {
		case "taskName": {
			taskToUpdate.setName(value.toString()); break;
		}
		case "assignedstage": {
			taskToUpdate.setAssignedstage((int) value); break;
		}
		case "taskDescription": {
			taskToUpdate.setTaskDescription(value.toString()); break;
		}
		case "remainingworkload": {
			taskToUpdate.adjustRemainingworkload((int) value); break;
		}
		case "priority": {
			taskToUpdate.setTaskPriority((byte) value); break;
		}
		default:
			throw new IllegalArgumentException("Feldname nicht erlaubt zu ändern oder nicht existent / korrekt: " + fieldname);
		}
		taskToUpdate.setLastchangeDate(getCurrentTime());
		return taskToUpdate;		
	}

	public Boolean UpdateWasToFinalLastStageOfBoard(int lastStageId, int oldStageId, int newStageId) {
		return ((oldStageId != newStageId) && lastStageId == newStageId);
	}
}
