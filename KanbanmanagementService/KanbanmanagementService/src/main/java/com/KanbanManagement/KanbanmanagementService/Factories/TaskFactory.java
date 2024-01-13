package com.KanbanManagement.KanbanmanagementService.Factories;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskFactory {

	// Enum To Int/Byte Cast Referenz: https://stackoverflow.com/questions/5878952/cast-int-to-enum-in-java
	public TaskEntity ConvertToEntity(Task task) {
		TaskId taskId = task.getTaskId();
		StageId assignedstage = task.getAssignedstage();
		return new TaskEntity(taskId.getId(), task.getTaskName(), assignedstage.getId(), task.getTaskDescription(), 
				task.getRemainingworkload(), task.getCreationdate(), (byte) task.getTasktype().ordinal(), task.getLastchangeDate(), task.getPriority());
	}
	
	public Task ConvertToAggregate(TaskEntity taskEntity) {
		TaskId taskId = new TaskId (taskEntity.getId());
		StageId assignedstage = new StageId(taskEntity.getAssignedstage());
		TaskType taskType = TaskType.values()[taskEntity.getTasktype()];
		return new Task(taskId, taskEntity.getName(), assignedstage, taskEntity.getTaskDescription(), 
				taskEntity.getRemainingworkload(), taskEntity.getCreationdate(), taskType, taskEntity.getLastchangeDate(), taskEntity.getTaskPriority());	
	}
}