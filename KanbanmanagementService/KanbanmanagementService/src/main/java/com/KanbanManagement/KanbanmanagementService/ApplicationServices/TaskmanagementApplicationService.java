package com.KanbanManagement.KanbanmanagementService.ApplicationServices;

import java.sql.Date;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

public class TaskmanagementApplicationService {

	public Task test() {
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
}
