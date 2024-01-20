package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;

public interface JdbcTaskReportDataRepository extends CrudRepository<TaskReportEntity, Integer>{

}
