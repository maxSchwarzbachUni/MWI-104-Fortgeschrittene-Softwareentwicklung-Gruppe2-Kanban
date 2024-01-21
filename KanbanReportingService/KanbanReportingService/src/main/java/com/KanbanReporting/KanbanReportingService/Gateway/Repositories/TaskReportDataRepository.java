package com.KanbanReporting.KanbanReportingService.Gateway.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;

@Component
public class TaskReportDataRepository implements ITaskReportDataRepository {
	private final JdbcTaskReportDataRepository jdbcTaskReportDataRepository;
	private final JdbcTaskReportByTaskIdRepository jdbcTaskReportByTaskIdRepository;
	
	@Autowired
	public TaskReportDataRepository(JdbcTaskReportDataRepository jdbcTaskReportDataRepository, JdbcTaskReportByTaskIdRepository jdbcTaskReportByTaskIdRepository) {
		this.jdbcTaskReportDataRepository = jdbcTaskReportDataRepository;
		this.jdbcTaskReportByTaskIdRepository = jdbcTaskReportByTaskIdRepository;
	}

	@Override
	public TaskReportEntity getTaskReportById(int id) {
		Optional<TaskReportEntity> taskReportEntity = jdbcTaskReportDataRepository.findById(id);
		if(taskReportEntity.isPresent()) {
			return taskReportEntity.get();
		}
		return null;
	}
	
	@Override
	public TaskReportEntity getTaskReportByTaskId(int id) {		
		List<TaskReportEntity> taskReportEntityList = jdbcTaskReportByTaskIdRepository.findTaskReportdByTaskid(id);
		if(!taskReportEntityList.isEmpty()) {
			return taskReportEntityList.get(taskReportEntityList.size()-1);
		}
		return null;
	}

	@Override
	public Iterable<TaskReportEntity> getAllTasksReportsOfKanbanId(int id) {
		Optional<Iterable<TaskReportEntity>> taskReportEntityList = Optional.ofNullable(jdbcTaskReportDataRepository.findAll());
        if (taskReportEntityList.isPresent()) {
        	List<TaskReportEntity> result = new ArrayList<>();
        	for (TaskReportEntity taskReportEntity : taskReportEntityList.get()) {
				if(taskReportEntity.getKanbanboardId() == id) {
					result.add(taskReportEntity);
				}
			}        	
            return result;
        } 
        return null;	
	}

	@Override
	public TaskReportEntity saveTaskReport(TaskReportEntity taskReportEntity) {
		if(taskReportEntity != null) {
			return jdbcTaskReportDataRepository.save(taskReportEntity);			
		}
		return null;
	}
	
}
