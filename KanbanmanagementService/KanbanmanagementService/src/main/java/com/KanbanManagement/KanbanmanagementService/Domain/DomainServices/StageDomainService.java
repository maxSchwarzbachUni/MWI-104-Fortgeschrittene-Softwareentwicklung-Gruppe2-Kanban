package com.KanbanManagement.KanbanmanagementService.Domain.DomainServices;

import java.util.List;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.StageFactory;

public class StageDomainService {
	
	
	StageFactory stageFactory;
	
	public StageDomainService(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

	public Stage ConvertStageEntityToAggregate(StageEntity stageEntity) {
		return stageFactory.ConvertToAggregate(stageEntity);
	}

	public List<Stage> ConvertStageEntityListToStageList(Iterable<StageEntity> stageEntityList) {
		return stageFactory.ConvertStageEntityListToStageList(stageEntityList);
	}

}
