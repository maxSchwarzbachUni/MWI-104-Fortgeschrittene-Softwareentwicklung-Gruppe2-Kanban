package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;

@Repository
public interface JdbcKanbanDashboardRepository extends CrudRepository<KanbanDashboardEntity, Integer> {

}
