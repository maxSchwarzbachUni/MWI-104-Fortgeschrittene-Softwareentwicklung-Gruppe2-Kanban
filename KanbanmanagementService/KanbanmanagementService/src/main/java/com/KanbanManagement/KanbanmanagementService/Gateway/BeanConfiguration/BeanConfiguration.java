package com.KanbanManagement.KanbanmanagementService.Gateway.BeanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.TaskmanagementDomainService;
import com.KanbanManagement.KanbanmanagementService.Domain.Factories.TaskFactory;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.ITaskRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.JdbcStageEntityRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.JdbcStagePositionExistsRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.JdbcTaskEntityRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.StageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.TaskRepository;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.StageApplicationService;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskNotificationEmitterService;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskmanagementApplicationService;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 TaskmanagementApplicationService taskmanagementApplicationService(ITaskRepository taskRepository, TaskmanagementDomainService taskmanagementDomainService, IStageRepository stageRepository) {
	        return new TaskmanagementApplicationService(taskRepository, taskmanagementDomainService, stageRepository);
	 }
	 
	 @Bean 
	 TaskmanagementDomainService taskmanagementDomainService() {
		 return new TaskmanagementDomainService(new TaskNotificationEmitterService(), new TaskFactory());
	 }
	 
	 @Bean
	 ITaskRepository taskRepository(JdbcTaskEntityRepository jdbcTaskEntityRepository) {
	        return new TaskRepository(jdbcTaskEntityRepository);
	 }
	 
	 @Bean
	 StageApplicationService stagemanagementApplicationService(IStageRepository stageRepository) {
	        return new StageApplicationService(stageRepository);
	 }
	 
	 @Bean
	 IStageRepository stageRepository(JdbcStageEntityRepository jdbcStageEntityRepository, JdbcStagePositionExistsRepository jdbcStagePositionExistsRepository) {
	        return new StageRepository(jdbcStageEntityRepository, jdbcStagePositionExistsRepository);
	 }
}