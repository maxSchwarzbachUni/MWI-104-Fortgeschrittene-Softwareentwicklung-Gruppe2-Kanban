package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;

@Repository
public interface JdbcStageEntityRepository 
	extends CrudRepository<StageEntity, Integer> {
}
