package com.KanbanManagement.KanbanmanagementService.Repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
//import com.KanbanManagement.KanbanmanagementService.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Entities.TaskEntity;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;


// Referenz: https://www.geeksforgeeks.org/spring-boot-h2-database/ 

@Component
public class TaskRepository{
	
	private final JdbcTaskEntityRepository jdbcTaskEntityRepository;
	
	@Autowired
	public TaskRepository(JdbcTaskEntityRepository jdbcTaskEntityRepository) {
		this.jdbcTaskEntityRepository = jdbcTaskEntityRepository;
	}
	
	public TaskEntity findById(TaskId taskId) {
		Optional<TaskEntity> taskEntity = jdbcTaskEntityRepository.findById(taskId.getId());
        if (taskEntity.isPresent()) {
            return taskEntity.get();
        } else {
            return null;
        }
	}

	public Iterable<TaskEntity> getAllTasks() {
		Optional<Iterable<TaskEntity>> tasklist = Optional.ofNullable(jdbcTaskEntityRepository.findAll());
        if (tasklist.isPresent()) {
            return tasklist.get();
        } else {
            return null;
        }		
	}
	
	public boolean updateTask(TaskEntity taskToUpdate) {
		if(taskToUpdate != null && jdbcTaskEntityRepository.existsById(taskToUpdate.getId())) {
			jdbcTaskEntityRepository.save(taskToUpdate);
			return true;
		}		
		return false;
	}
}
