package com.KanbanManagement.KanbanmanagementService.Domain.Factories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;

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
	
	public ResponseEntity<Object> ConvertTaskListToTaskEntityList(Iterable<TaskEntity> taskEntityList) {
		List<Task> taskList = new ArrayList<Task>();
		for (TaskEntity taskEntity : taskEntityList) {
			var convertedTask = ConvertToAggregate(taskEntity);
			taskList.add(convertedTask);
		}
		return new ResponseEntity<Object>((Task[]) taskList.toArray(new Task[taskList.size()]), HttpStatus.OK);
	}
}