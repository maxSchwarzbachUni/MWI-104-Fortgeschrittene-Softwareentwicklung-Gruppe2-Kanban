package com.KanbanManagement.KanbanmanagementService.Domain.Factories;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Kanbanboard;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.KanbanboardEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.KanbanId;

public class KanbanboardFactory {

	public KanbanboardEntity ConvertToEntity(Kanbanboard kanbanboard) {
		KanbanId kanbanId = kanbanboard.getKanbanId();
		return new KanbanboardEntity(kanbanId.getId(), kanbanboard.getName());
	}
	
	public Kanbanboard ConvertToAggregate(KanbanboardEntity kanbanboardEntity) {
		KanbanId kanbanId = new KanbanId (kanbanboardEntity.getId());
		return new Kanbanboard(kanbanId, kanbanboardEntity.getKanbanboardName());		
	}
}
