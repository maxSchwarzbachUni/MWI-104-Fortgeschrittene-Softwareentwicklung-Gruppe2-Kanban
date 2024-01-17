package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;

public interface ITaskRepository {

	TaskEntity findById(TaskId taskId);

	Iterable<TaskEntity> getAllTasks();

	boolean updateTask(TaskEntity taskToUpdate);

	TaskEntity saveTask(TaskEntity taskToSave);

}