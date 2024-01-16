package com.KanbanManagement.KanbanmanagementService.Gateway.Repositories;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanManagement.KanbanmanagementService.Domain.Entities.StageEntity;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;

@Component
public class StageRepository {

	private final JdbcStageEntityRepository jdbcStageEntityRepository;
	private final JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository;
	
	@Autowired
	public StageRepository(JdbcStageEntityRepository jdbcStageEntityRepository, JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository) {
		this.jdbcStageEntityRepository = jdbcStageEntityRepository;
		this.jdbcStagePositionExistsRepository = jdbcStagePositionExistsRepository;
	}
	
	public StageEntity InsertNewStage(StageEntity stageEntity) {
		if(stageEntity != null) {
			return jdbcStageEntityRepository.save(stageEntity);			
		}
		return null;
	}
	
	public StageEntity findById(StageId stageId) {
		Optional<StageEntity> stageEntity = jdbcStageEntityRepository.findById(stageId.getId());
        if (stageEntity.isPresent()) {
            return stageEntity.get();
        } else {
            return null;
        }
	}
	
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
}
