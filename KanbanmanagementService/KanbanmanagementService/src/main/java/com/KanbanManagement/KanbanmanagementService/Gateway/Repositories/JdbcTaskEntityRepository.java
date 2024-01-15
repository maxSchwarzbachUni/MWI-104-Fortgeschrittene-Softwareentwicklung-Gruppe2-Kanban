package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskEntity;

@Repository
public interface JdbcTaskEntityRepository 
	extends CrudRepository<TaskEntity, Integer> {

}

