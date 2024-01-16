package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.TaskmanagementDomainService;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.StageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.TaskRepository;

public class TaskmanagementApplicationService {
	
	private TaskRepository taskRepository;
	private StageRepository stageRepository;
	private TaskmanagementDomainService taskmanagementDomainService;
	
	public TaskmanagementApplicationService(TaskRepository taskRepository, TaskmanagementDomainService taskmanagementDomainService, StageRepository stageRepository) {
		this.taskRepository = taskRepository;
		this.taskmanagementDomainService = taskmanagementDomainService;
		this.stageRepository = stageRepository;
	}

	public ResponseEntity<Object> HandleGetAllTasksRequest() {
		Iterable<TaskEntity> taskEntityList = taskRepository.getAllTasks();
		return taskmanagementDomainService.ConvertTaskListToTaskEntityList(taskEntityList);	
	}

	public ResponseEntity<Object> HandleGetTaskById(int taskId) {
		TaskEntity foundTaskEntity = taskRepository.findById(new TaskId(taskId));
		if(foundTaskEntity == null) {
			new ResponseEntity<Object> ("Kein Task gefunden für die id: " + taskId, HttpStatus.OK);
		}
		return new ResponseEntity<Object> (taskmanagementDomainService.ConvertTaskToAggregate(foundTaskEntity), HttpStatus.OK);
	}

	public ResponseEntity<Object> HandleUpdateAssignedStage(int id, int stageId) {
		TaskEntity taskToUpdate  = taskRepository.findById(new TaskId(id));
		int oldStageId = taskToUpdate.getAssignedstage();
		StageEntity stageEntity = stageRepository.findById(new StageId(stageId));
		
		taskmanagementDomainService.checkTaskAndStageEntities(taskToUpdate, stageEntity);
		TaskEntity taskToSave = taskmanagementDomainService.updateAssignedStage(taskToUpdate, stageId);	
		Boolean udpateResult = taskRepository.updateTask(taskToSave);
		if(!udpateResult) {
			return new ResponseEntity<Object>(TaskManagementKonstanten.task_udpate_failed_task_do_not_exist, HttpStatus.NOT_MODIFIED);
		}
		
		System.out.println(taskmanagementDomainService.createAndSendTaskUpdateNotification(taskToUpdate, stageEntity.getKanbanid()));
		String resultMessage = TaskManagementKonstanten.task_udpate_succesful + " for id " + id + " from stage " + oldStageId + " to stage " + taskToSave.getAssignedstage();
		return new ResponseEntity<Object>(resultMessage, HttpStatus.OK);
	}

	public ResponseEntity<Object> HandlePostNewTask(Task task) {
		return null;
	}
}
