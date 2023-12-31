package com.KanbanManagement.KanbanmanagementService.ApplicationServices;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Factories.StageFactory;
import com.KanbanManagement.KanbanmanagementService.Repositories.StageRepository;

public class StagemanagementApplicationService {
	private StageRepository stageRepository;
	private StageFactory stageFactory;
	
	public StagemanagementApplicationService(StageRepository stageRepository) {
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
