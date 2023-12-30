package com.KanbanManagement.KanbanmanagementService.ApplicationServices;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.Factories.TaskFactory;
import com.KanbanManagement.KanbanmanagementService.Repositories.TaskRepository;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskmanagementApplicationService {
	
	private TaskRepository taskRepository;
	private TaskFactory taskFactory;
	
	public TaskmanagementApplicationService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
		this.taskFactory = new TaskFactory();
	}

	public Task test() {
		TaskEntity t = taskRepository.findById(new TaskId(3));
		
		Date da = t.getLastchangeDate();
		
		TaskId taskId = new TaskId(1);
		StageId assignedstage = new StageId(2);
		double d = 0.0;
		Date now = new Date(System.currentTimeMillis()); // https://stackoverflow.com/questions/5175728/how-to-get-the-current-date-time-in-java
		TaskType bugfix = TaskType.BugFix;
		
		Task task = new Task(taskId, "test", assignedstage, "das ist ein test task", d, 
				now, bugfix, now, (byte) 3);
		//System.out.println(task);
		return task;
	}

	public Task[] HandleGetAllTasksRequest() {
		List<Task> taskList = new ArrayList<Task>();
		
		Iterable<TaskEntity> taskEntityList = taskRepository.getAllTasks();
		
		for (TaskEntity taskEntity : taskEntityList) {
			var convertedTask = taskFactory.ConvertToAggregate(taskEntity);
			taskList.add(convertedTask);
		}
		
		return (Task[]) taskList.toArray(new Task[taskList.size()]);
	}

	public Task HandleGetTaskById(int taskId) {
		TaskEntity foundTaskEntity = taskRepository.findById(new TaskId(taskId));
		if(foundTaskEntity == null) {
			return null;
		}
		return taskFactory.ConvertToAggregate(foundTaskEntity);
	}
}
