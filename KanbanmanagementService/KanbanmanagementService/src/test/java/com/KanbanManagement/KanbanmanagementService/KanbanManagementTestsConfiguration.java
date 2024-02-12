package com.KanbanManagement.KanbanmanagementService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices.KafkaMessageEmitterService;
import com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices.RabbitMqMessageEmitterService;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.ITaskRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.StageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.TaskRepository;

@Profile("test")
@Configuration
public class KanbanManagementTestsConfiguration {
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
	   
	   @Bean
	   @Primary 
	   public KafkaMessageEmitterService kafkaMessageEmitterService1() {
		   return Mockito.mock(KafkaMessageEmitterService.class);
	   }
	   
	   @Bean
	   @Primary
	   public RabbitMqMessageEmitterService rabbitMqMessageEmitterService1() {
		   return Mockito.mock(RabbitMqMessageEmitterService.class);
	   }
}
