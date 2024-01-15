package com.KanbanManagement.KanbanmanagementService.Domain.Factories;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

public class StageFactory {

	public StageEntity ConvertToEntity(Stage stage) {
		StageId stageId = stage.getId();
		return new StageEntity(stageId.getId(), stage.getName(), stage.getPosition());
	}
	
	public Stage ConvertToAggregate(StageEntity stageEntity) {
		StageId stageId = new StageId (stageEntity.getId());
		return new Stage(stageId, stageEntity.getStageName(), stageEntity.getPosition());		
	}
}
