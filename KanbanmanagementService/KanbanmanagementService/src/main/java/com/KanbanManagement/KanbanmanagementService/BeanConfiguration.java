package com.KanbanManagement.KanbanmanagementService;

//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
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
	 TaskmanagementApplicationService taskmanagementApplicationService(TaskRepository taskRepository, TaskmanagementDomainService taskmanagementDomainService) {
	        return new TaskmanagementApplicationService(taskRepository, taskmanagementDomainService);
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
	 
//	 @Bean
//	 TaskFactory taskFactory(TaskFactory taskFactory) {
//	        return new TaskFactory();
//	 }
	 
//	 @Bean
//	 MessageQueue messageQueue(AmqpTemplate amqpTemplate) {
//		 return new QueueAdapter(amqpTemplate);
//	 }
//	 
//	 @Bean
//	 public Queue myQueue() {
//		 return new Queue(QueueAdapter.MY_QUEUE_NAME, QueueAdapter.NON_DURABLE);    
//	 }
	 
}