package com.KanbanManagement.KanbanmanagementService.Repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.KanbanManagement.KanbanmanagementService.Entities.StageEntity;

// Referenz: https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
public interface JdbcStagePositionExistsRepository extends Repository<StageEntity, Integer>  {
	List<StageEntity> findStageByPosition(int position);
}
