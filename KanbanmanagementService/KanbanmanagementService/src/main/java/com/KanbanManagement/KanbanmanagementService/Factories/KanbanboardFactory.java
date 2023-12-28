package com.KanbanManagement.KanbanmanagementService.Factories;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Kanbanboard;
import com.KanbanManagement.KanbanmanagementService.Entities.KanbanboardEntity;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.KanbanId;

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
