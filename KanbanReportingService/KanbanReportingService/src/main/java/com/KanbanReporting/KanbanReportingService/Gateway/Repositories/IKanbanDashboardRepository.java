package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.List;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;

public interface IKanbanDashboardRepository {

	KanbanDashboardEntity getKanbanReportForKanbanId(int id);

	KanbanDashboardEntity saveNewKanbanDashboard(KanbanDashboardEntity kanbanDashboardToSave);
	
	List<KanbanDashboardEntity> getAllKanbanReportsForKanbanId(int id);
}