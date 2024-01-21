package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;

public interface ITaskReportDataRepository {

	TaskReportEntity getTaskReportById(int id);

	Iterable<TaskReportEntity> getAllTasksReportsOfKanbanId(int id);

	TaskReportEntity saveTaskReport(TaskReportEntity taskReportEntity);
	
	TaskReportEntity getTaskReportByTaskId(int id);
}