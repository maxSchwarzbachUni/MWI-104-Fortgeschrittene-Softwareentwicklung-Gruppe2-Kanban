package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;

public interface IKanbanDashboardRepository {

	KanbanDashboardEntity getKanbanReportForId(int id);

}