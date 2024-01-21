package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;


public interface JdbcKanbanDashboardByKanbanIdRepository extends Repository<KanbanDashboardEntity, Integer>{
	List<KanbanDashboardEntity> findKanbanDashboardByKanbanid(int kanbanboardid);
}
