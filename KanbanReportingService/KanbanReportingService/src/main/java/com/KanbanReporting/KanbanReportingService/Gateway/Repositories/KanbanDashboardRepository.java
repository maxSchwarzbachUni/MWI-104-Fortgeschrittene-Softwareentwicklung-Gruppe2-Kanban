package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;

@Component
public class KanbanDashboardRepository implements IKanbanDashboardRepository {
	
	private final JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository;
	private final JdbcKanbanDashboardByKanbanIdRepository jdbcKanbanDashboardByKanbanIdRepository;
	
	@Autowired
	public KanbanDashboardRepository(JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository, JdbcKanbanDashboardByKanbanIdRepository jdbcKanbanDashboardByKanbanIdRepository) {
		this.jdbcKanbanDashboardRepository = jdbcKanbanDashboardRepository;
		this.jdbcKanbanDashboardByKanbanIdRepository = jdbcKanbanDashboardByKanbanIdRepository;
	}

	@Override
	public KanbanDashboardEntity getKanbanReportForKanbanId(int id) {
		List<KanbanDashboardEntity> kanbanDashboardList = getAllKanbanReportsForKanbanId(id);
		return kanbanDashboardList.get(kanbanDashboardList.size()-1);
	}
	
	@Override
	public List<KanbanDashboardEntity> getAllKanbanReportsForKanbanId(int id) {
		List<KanbanDashboardEntity> kanbanDashboardList = jdbcKanbanDashboardByKanbanIdRepository.findKanbanDashboardByKanbanid(id);
		if(!kanbanDashboardList.isEmpty()) {
			return kanbanDashboardList;
		}
		return null;
	}

	@Override
	public KanbanDashboardEntity saveNewKanbanDashboard(KanbanDashboardEntity kanbanDashboardToSave) {
		if(kanbanDashboardToSave != null) {
			return jdbcKanbanDashboardRepository.save(kanbanDashboardToSave);
		}
		return null;
	}
}
