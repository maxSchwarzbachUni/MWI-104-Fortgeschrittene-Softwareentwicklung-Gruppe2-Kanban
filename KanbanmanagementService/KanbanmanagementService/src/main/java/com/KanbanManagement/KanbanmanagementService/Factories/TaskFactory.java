package com.KanbanManagement.KanbanmanagementService.Factories;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskFactory {

	public TaskEntity ConvertToEntity(Task task) {
		TaskId taskId = task.getTaskId();
		StageId assignedstage = task.getAssignedstage();
		return new TaskEntity(taskId.getId(), task.getTaskName(), assignedstage.getId(), task.getTaskDescription(), 
				task.getRemainingworkload(), task.getCreationdate(), task.getTasktype(), task.getLastchangeDate(), task.getPriority());
	}
	
	public Task ConvertToAggregate(TaskEntity taskEntity) {
		TaskId taskId = new TaskId (taskEntity.getId());
		StageId assignedstage = new StageId(taskEntity.getAssignedstage());
		return new Task(taskId, taskEntity.getName(), assignedstage, taskEntity.getTaskDescription(), 
				taskEntity.getRemainingworkload(), taskEntity.getCreationdate(), taskEntity.getTasktype(), taskEntity.getLastchangeDate(), taskEntity.getTaskPriority());	
	}
}