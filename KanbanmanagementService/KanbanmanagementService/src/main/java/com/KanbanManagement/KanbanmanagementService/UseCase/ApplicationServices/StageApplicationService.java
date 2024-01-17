package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.StageFactory;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;

public class StageApplicationService {
	private IStageRepository stageRepository;
	private StageFactory stageFactory;
	
	public StageApplicationService(IStageRepository stageRepository) {
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

	public ResponseEntity<Object> HandleGetAllStages() {
		Iterable<StageEntity> stageEntityList = stageRepository.getAllStages();
		List<Stage> stageList = stageFactory.ConvertStageEntityListToStageList(stageEntityList);
		return new ResponseEntity<Object>(stageList.toArray(), HttpStatus.OK);
	}

	public ResponseEntity<Object> HandleGetStageById(int id) {
		StageEntity foundStageEntity = stageRepository.findById(new StageId(id));
		if(foundStageEntity == null) {
			return new ResponseEntity<Object>("Stage mit id " + id + " konnte leider nicht gefunden werden",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(stageFactory.ConvertToAggregate(foundStageEntity), HttpStatus.OK);
	}
}
