package com.KanbanReporting.KanbanReportingService.Domain.Factories;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;
import com.KanbanReporting.KanbanReportingService.Domain.ValueObjects.KanbanDashboardId;

public class KanbanDashboardFactory {

	public KanbanDashboardEntity ConvertToEntity(KanbanDashboard kanbanDashboard) {
		int kanbanDashboardId = kanbanDashboard.getId().getId();
		return new KanbanDashboardEntity(
				kanbanDashboardId, 
				kanbanDashboard.getKanbanId(), 
				kanbanDashboard.getAvgLeadTime(), 
				kanbanDashboard.getAvgIdleTime(), 
				kanbanDashboard.getAvgUnfinishedProcessingTimeInHours(), 
				kanbanDashboard.getOldestActiveTask(), 
				kanbanDashboard.getOldestActiveTaskTimeInHours(), 
				kanbanDashboard.getDashboardCreationDate());
	}
	
	public KanbanDashboardEntity ConvertToEntityWithoutId(KanbanDashboard kanbanDashboard) {
		return new KanbanDashboardEntity(				
				kanbanDashboard.getKanbanId(), 
				kanbanDashboard.getAvgLeadTime(), 
				kanbanDashboard.getAvgIdleTime(), 
				kanbanDashboard.getAvgUnfinishedProcessingTimeInHours(), 
				kanbanDashboard.getOldestActiveTask(), 
				kanbanDashboard.getOldestActiveTaskTimeInHours(), 
				kanbanDashboard.getDashboardCreationDate());
	}
	
	public KanbanDashboard ConvertToAggregate(KanbanDashboardEntity kanbanDashboardEntity) {
		KanbanDashboardId kanbanDashboardId = new KanbanDashboardId (kanbanDashboardEntity.getId());
		return new KanbanDashboard(
				kanbanDashboardId, 
				kanbanDashboardEntity.getKanbanId(), 
				kanbanDashboardEntity.getAvgLeadTime(), 
				kanbanDashboardEntity.getAvgIdleTime(),
				kanbanDashboardEntity.getAvgUnfinishedProcessingTime(),
				kanbanDashboardEntity.getOldestActiveTask(),
				kanbanDashboardEntity.getOldestActiveTaskTime(), 
				kanbanDashboardEntity.getDashboardCreationDate());	
	}
}
