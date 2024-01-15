package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.StageFactory;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.StageRepository;

public class StageApplicationService {
	private StageRepository stageRepository;
	private StageFactory stageFactory;
	
	public StageApplicationService(StageRepository stageRepository) {
		this.stageRepository = stageRepository;
		this.stageFactory = new StageFactory();
	}

	public Stage HandlePostNewStage(String name, int position) {
		boolean isStagePositionAlreadyInUse = stageRepository.isStagePositionAlreadyInUse(position);
		if(isStagePositionAlreadyInUse) {
			System.out.println("Anlage der Stage nicht möglich, weil die Position bereits verwendet wird.");
			return null;
		}
		
		StageEntity stageEntityToSafe = new StageEntity(name, position);
		
		try {
			StageEntity stageEntity = stageRepository.InsertNewStage(stageEntityToSafe);
			return stageFactory.ConvertToAggregate(stageEntity);
		} 
		catch (Exception e) {
			System.out.println("Anlegen der neuen Stage ist fehlgeschlagen! Fehlermeldung: " + e.getMessage());
		}
		System.out.println("Stage wurde nicht angelegt. Grund unbekannt.");
		return null;
	}
}
