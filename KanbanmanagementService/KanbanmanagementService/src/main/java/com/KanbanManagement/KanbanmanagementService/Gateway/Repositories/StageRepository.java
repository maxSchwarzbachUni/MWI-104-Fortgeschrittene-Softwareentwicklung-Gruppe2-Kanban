package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

@Component
public class StageRepository implements IStageRepository {

	private final JdbcStageEntityRepository jdbcStageEntityRepository;
	private final JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository;
	
	@Autowired
	public StageRepository(JdbcStageEntityRepository jdbcStageEntityRepository, JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository) {
		this.jdbcStageEntityRepository = jdbcStageEntityRepository;
		this.jdbcStagePositionExistsRepository = jdbcStagePositionExistsRepository;
	}
	
	@Override
	public StageEntity InsertNewStage(StageEntity stageEntity) {
		if(stageEntity != null) {
			return jdbcStageEntityRepository.save(stageEntity);			
		}
		return null;
	}
	
	@Override
	public StageEntity findById(StageId stageId) {
		Optional<StageEntity> stageEntity = jdbcStageEntityRepository.findById(stageId.getId());
        if (stageEntity.isPresent()) {
            return stageEntity.get();
        } else {
            return null;
        }
	}
	
	@Override
	public boolean isStagePositionAlreadyInUse(int position, int kanbanid) {
		List<StageEntity> foundStageEntities = jdbcStagePositionExistsRepository.findStageByPosition(position);
		for (Iterator<StageEntity> iterator = foundStageEntities.iterator(); iterator.hasNext();) {
			StageEntity stageEntity = (StageEntity) iterator.next();
			if(stageEntity.getKanbanid() == kanbanid) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterable<StageEntity> getAllStages() {
		Optional<Iterable<StageEntity>> stageList = Optional.ofNullable(jdbcStageEntityRepository.findAll());
        if (stageList.isPresent()) {
            return stageList.get();
        } else {
            return null;
        }		
	}
	
	public int getLastStage(int kanbanId) {
		List<StageEntity> foundStageEntities = jdbcStagePositionExistsRepository.findStageByKanbanid(kanbanId);
		int lastStagePosition = foundStageEntities
			      .stream()
			      .mapToInt(v -> v.getPosition())
			      .max().orElseThrow(NoSuchElementException::new);
		
		List<StageEntity> newStageEntities = jdbcStagePositionExistsRepository.findStageByPosition(lastStagePosition);
		int stageId = 0;
		for (StageEntity stageEntity : newStageEntities) {
			if(stageEntity.getKanbanid() == kanbanId) {
				stageId = stageEntity.getId();
			}
		}
		
		return stageId;
	}
}
