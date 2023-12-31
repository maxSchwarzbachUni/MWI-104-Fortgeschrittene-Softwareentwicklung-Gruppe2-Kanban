package com.KanbanManagement.KanbanmanagementService.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.KanbanManagement.KanbanmanagementService.Entities.StageEntity;

@Repository
public interface JdbcStageEntityRepository 
	extends CrudRepository<StageEntity, Integer> {

}
