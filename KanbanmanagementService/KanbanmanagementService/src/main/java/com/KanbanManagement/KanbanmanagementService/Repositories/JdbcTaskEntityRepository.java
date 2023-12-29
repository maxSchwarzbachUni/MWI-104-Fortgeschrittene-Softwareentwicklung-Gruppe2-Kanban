package com.KanbanManagement.KanbanmanagementService.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;

@Repository
public interface JdbcTaskEntityRepository 
	extends CrudRepository<TaskEntity, Integer> {

}

