package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.DomainResult;
import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.TaskmanagementDomainService;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;
import com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices.CommunicationType;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.ITaskRepository;

public class TaskmanagementApplicationService {
	
	private ITaskRepository taskRepository;
	private IStageRepository stageRepository;
	private TaskmanagementDomainService taskmanagementDomainService;
	
	public TaskmanagementApplicationService(ITaskRepository taskRepository, TaskmanagementDomainService taskmanagementDomainService, IStageRepository stageRepository) {
		this.taskRepository = taskRepository;
		this.taskmanagementDomainService = taskmanagementDomainService;
		this.stageRepository = stageRepository;
	}

	public ResponseEntity<Object> HandleGetAllTasksRequest() {
		Iterable<TaskEntity> taskEntityList = taskRepository.getAllTasks();
		return new ResponseEntity<Object>(taskmanagementDomainService.ConvertTaskListToTaskEntityList(taskEntityList), HttpStatus.OK);	
	}

	public ResponseEntity<Object> HandleGetTaskById(int taskId) {
		TaskEntity foundTaskEntity = taskRepository.findById(new TaskId(taskId));
		if(foundTaskEntity == null) {
			new ResponseEntity<Object> ("Kein Task gefunden für die id: " + taskId, HttpStatus.OK);
		}
		return new ResponseEntity<Object> (taskmanagementDomainService.ConvertTaskToAggregate(foundTaskEntity), HttpStatus.OK);
	}

	public ResponseEntity<Object> HandleUpdateAssignedStage(int id, int stageId, CommunicationType communicationType) {
		TaskEntity taskToUpdate  = taskRepository.findById(new TaskId(id));
		
		DomainResult checkTaskResult = taskmanagementDomainService.checkTaskEntity(taskToUpdate);
		if(!checkTaskResult.ActionSuccesful) {
			return new ResponseEntity<Object>(checkTaskResult.Message, checkTaskResult.statusCode);
		}
		
		int oldStageId = taskToUpdate.getAssignedstage();
		StageEntity stageEntity = stageRepository.findById(new StageId(stageId));
		DomainResult checkStageResult =taskmanagementDomainService.checkStageEntity(stageEntity);
		if(!checkStageResult.ActionSuccesful) {
			return new ResponseEntity<Object>(checkStageResult.Message, checkStageResult.statusCode);
		}	
		
		TaskEntity taskToSave = taskmanagementDomainService.updateAssignedStage(taskToUpdate, stageId);	
		Boolean udpateResult = taskRepository.updateTask(taskToSave);
		if(!udpateResult) {
			return new ResponseEntity<Object>(TaskManagementKonstanten.task_udpate_failed_task_do_not_exist, HttpStatus.NOT_MODIFIED);
		}
		
		Boolean updateToLastStage = taskmanagementDomainService.UpdateWasToFinalLastStageOfBoard(stageRepository.getLastStage(stageEntity.getKanbanid()), oldStageId, stageEntity.getId());
		
		taskmanagementDomainService.createAndSendTaskUpdateNotification(taskToUpdate, stageEntity.getKanbanid(), updateToLastStage, communicationType);
		String resultMessage = TaskManagementKonstanten.task_udpate_succesful + " for id " + id + " from stage " + oldStageId + " to stage " + taskToSave.getAssignedstage();
		return new ResponseEntity<Object>(resultMessage, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> HandlePostNewTask(Task taskToCreate, CommunicationType communicationType) {
		DomainResult checkTaskResult = taskmanagementDomainService.validateNewTaskData(taskToCreate);
		if(!checkTaskResult.ActionSuccesful) {
			return new ResponseEntity<Object>(checkTaskResult.Message, checkTaskResult.statusCode);
		}
		Task taskToSave = taskmanagementDomainService.AmendNewTask(taskToCreate);
		TaskEntity taskEntityToSave = taskmanagementDomainService.getTaskEntityFromAggrate(taskToSave);
		TaskEntity resultingTaskEntity = taskRepository.saveTask(taskEntityToSave);
		if(resultingTaskEntity == null) {
			return new ResponseEntity<Object>("Task could not be saved", HttpStatus.NOT_MODIFIED);
		}
		
		StageEntity stageEntity = stageRepository.findById(new StageId(taskEntityToSave.getAssignedstage()));
		Boolean updateToLastStage = taskmanagementDomainService.UpdateWasToFinalLastStageOfBoard(stageRepository.getLastStage(stageEntity.getKanbanid()), taskToCreate.getAssignedstage().getId(), stageEntity.getId());
		taskmanagementDomainService.createAndSendTaskUpdateNotification(resultingTaskEntity, stageEntity.getKanbanid(), updateToLastStage, communicationType);
		return new ResponseEntity<Object>("Task saved with id " + resultingTaskEntity.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> HandleUpdateTaskField(int id, String fieldname, Object value, CommunicationType communicationType) {
		TaskEntity taskToUpdate = taskRepository.findById(new TaskId(id));
		if(taskToUpdate == null) {
			return new ResponseEntity<Object>("Task to update was not found with id " + id, HttpStatus.NOT_FOUND);
		}
		int oldStageId = taskToUpdate.getAssignedstage();
			
		try {
			TaskEntity taskEntityToSave = taskmanagementDomainService.getUpdatedTaskEntityWithFieldChanged(taskToUpdate, fieldname, value);
			taskRepository.saveTask(taskEntityToSave);
			
			StageEntity stageEntity = stageRepository.findById(new StageId(taskEntityToSave.getAssignedstage()));
			Boolean updateToLastStage = taskmanagementDomainService.UpdateWasToFinalLastStageOfBoard(stageRepository.getLastStage(stageEntity.getKanbanid()), oldStageId, stageEntity.getId());
			taskmanagementDomainService.createAndSendTaskUpdateNotification(taskEntityToSave, stageEntity.getKanbanid(), updateToLastStage, communicationType);
			return new ResponseEntity<Object>("Task with id " + taskEntityToSave.getId() + " updated value of field " + fieldname + " to " + value , HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Object>("Task could not be updated, exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
