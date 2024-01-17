package com.KanbanManagement.KanbanmanagementService.Domain.Factories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

public class StageFactory {

	public StageEntity ConvertToEntity(Stage stage) {
		StageId stageId = stage.getId();
		return new StageEntity(stageId.getId(), stage.getName(), stage.getPosition(), stage.getKanbanid());
	}
	
	public Stage ConvertToAggregate(StageEntity stageEntity) {
		StageId stageId = new StageId (stageEntity.getId());
		return new Stage(stageId, stageEntity.getStageName(), stageEntity.getPosition(), stageEntity.getKanbanid());		
	}

	public List<Stage> ConvertStageEntityListToStageList(Iterable<StageEntity> stageEntityList) {
		List<Stage> stageList = new ArrayList<Stage>();
		for (StageEntity stageEntity : stageEntityList) {
			var convertedStage = ConvertToAggregate(stageEntity);
			stageList.add(convertedStage);
		}
		return stageList;
	}
}
