package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;

public class KanbanDashboardFactory {

	public KanbanDashboardEntity ConvertToEntity(KanbanDashboard kanbanDashboard) {
		int kanbanDashboardId = kanbanDashboard.getId().getId();
		return new KanbanDashboardEntity(kanbanDashboardId, kanbanDashboard.getKanbanId(), kanbanDashboard.getAvgLeadTime(), kanbanDashboard.getAvgCycleTime(), kanbanDashboard.getOldestActiveTask());
	}
	
	public KanbanDashboard ConvertToAggregate(KanbanDashboardEntity kanbanDashboardEntity) {
		KanbanDashboardId kanbanDashboardId = new KanbanDashboardId (kanbanDashboardEntity.getId());
		return new KanbanDashboard(kanbanDashboardId, kanbanDashboardEntity.getKanbanId(), kanbanDashboardEntity.getAvgLeadTime(), kanbanDashboardEntity.getAvgCycleTime(), kanbanDashboardEntity.getOldestActiveTask());	
	}
}
