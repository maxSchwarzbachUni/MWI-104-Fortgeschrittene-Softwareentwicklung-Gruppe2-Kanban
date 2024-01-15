package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;

// Referenz: https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
public interface JdbcStagePositionExistsRepository extends Repository<StageEntity, Integer>  {
	List<StageEntity> findStageByPosition(int position);
}
