package com.KanbanManagement.KanbanmanagementService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.ITaskRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.StageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.TaskRepository;

@Profile("test")
@Configuration
public class TaskmanagementDomainServiceTestsConfiguration {
	   @Bean
	   @Primary
	   public ITaskRepository taskService() {
	      return Mockito.mock(TaskRepository.class);
	   }
	   
	   @Bean
	   @Primary
	   public IStageRepository stageService() {
	      return Mockito.mock(StageRepository.class);
	   }
}
