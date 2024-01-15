package com.KanbanManagement.KanbanmanagementService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanManagement.KanbanmanagementService.ApplicationServices.StagemanagementApplicationService;
import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskChangedNotificationEmitterService;
import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskmanagementApplicationService;
import com.KanbanManagement.KanbanmanagementService.DomainServices.TaskmanagementDomainService;
import com.KanbanManagement.KanbanmanagementService.Factories.TaskFactory;
import com.KanbanManagement.KanbanmanagementService.Repositories.JdbcStageEntityRepository;
import com.KanbanManagement.KanbanmanagementService.Repositories.JdbcStagePositionExistsRepository;
import com.KanbanManagement.KanbanmanagementService.Repositories.JdbcTaskEntityRepository;
import com.KanbanManagement.KanbanmanagementService.Repositories.StageRepository;
import com.KanbanManagement.KanbanmanagementService.Repositories.TaskRepository;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 TaskmanagementApplicationService taskmanagementApplicationService(TaskRepository taskRepository, TaskmanagementDomainService taskmanagementDomainService, StageRepository stageRepository) {
	        return new TaskmanagementApplicationService(taskRepository, taskmanagementDomainService, stageRepository);
	 }
	 
	 @Bean 
	 TaskmanagementDomainService taskmanagementDomainService() {
		 return new TaskmanagementDomainService(new TaskChangedNotificationEmitterService());
	 }
	 
	 @Bean
	 TaskRepository taskRepository(JdbcTaskEntityRepository jdbcTaskEntityRepository) {
	        return new TaskRepository(jdbcTaskEntityRepository);
	 }
	 
	 @Bean
	 StagemanagementApplicationService stagemanagementApplicationService(StageRepository stageRepository) {
	        return new StagemanagementApplicationService(stageRepository);
	 }
	 
	 @Bean
	 StageRepository stageRepository(JdbcStageEntityRepository jdbcStageEntityRepository, JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository) {
	        return new StageRepository(jdbcStageEntityRepository, jdbcStagePositionExistsRepository);
	 }
}