package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;


public interface JdbcTaskReportByTaskIdRepository  extends Repository<TaskReportEntity, Integer>{
	List<TaskReportEntity> findTaskReportdByTaskid(int taskid);
}
