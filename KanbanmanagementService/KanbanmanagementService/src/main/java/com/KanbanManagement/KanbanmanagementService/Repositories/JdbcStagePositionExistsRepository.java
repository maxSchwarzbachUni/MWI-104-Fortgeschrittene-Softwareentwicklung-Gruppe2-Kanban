package com.KanbanManagement.KanbanmanagementService.Repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.KanbanManagement.KanbanmanagementService.Entities.StageEntity;

public interface JdbcStagePositionExistsRepository extends Repository<StageEntity, Integer>  {
	List<StageEntity> findStageByPosition(int position);
}
