package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

	public ResponseEntity<Object> HandlePostNewStage(String name, int position, int kanbanid) {
		boolean isStagePositionAlreadyInUse = stageRepository.isStagePositionAlreadyInUse(position, kanbanid);
		if(isStagePositionAlreadyInUse) {
			String positionAlreadyInUseMessage = "Anlage der Stage nicht möglich, weil die Position bereits verwendet wird.";
			System.out.println(positionAlreadyInUseMessage);
			return new ResponseEntity<Object>(positionAlreadyInUseMessage, HttpStatus.OK);
		}
		
		StageEntity stageEntityToSafe = new StageEntity(name, position, kanbanid);
		
		try {
			StageEntity stageEntity = stageRepository.InsertNewStage(stageEntityToSafe);
			return new ResponseEntity<Object>(stageFactory.ConvertToAggregate(stageEntity), HttpStatus.OK);
		} 
		catch (Exception e) {
			String errorMessage = "Anlegen der neuen Stage ist fehlgeschlagen! Fehlermeldung: " + e.getMessage();
			System.out.println(errorMessage);
			return new ResponseEntity<Object>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
