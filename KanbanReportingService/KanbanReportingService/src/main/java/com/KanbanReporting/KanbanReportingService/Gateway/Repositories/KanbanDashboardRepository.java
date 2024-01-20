package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;

@Component
public class KanbanDashboardRepository implements IKanbanDashboardRepository {
	
	private final JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository;
	
	@Autowired
	public KanbanDashboardRepository(JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository) {
		this.jdbcKanbanDashboardRepository = jdbcKanbanDashboardRepository;
	}

	@Override
	public KanbanDashboardEntity getKanbanReportForId(int id) {
		Optional<KanbanDashboardEntity> kanbanDashboardEntity = jdbcKanbanDashboardRepository.findById(id);
		if(kanbanDashboardEntity.isPresent()) {
			return kanbanDashboardEntity.get();
		}
		return null;
	}
}
