package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

public interface IStageRepository {

	StageEntity InsertNewStage(StageEntity stageEntity);

	StageEntity findById(StageId stageId);

	boolean isStagePositionAlreadyInUse(int position, int kanbanid);

	Iterable<StageEntity> getAllStages();
	
	int getLastStage(int kanbanid);

}