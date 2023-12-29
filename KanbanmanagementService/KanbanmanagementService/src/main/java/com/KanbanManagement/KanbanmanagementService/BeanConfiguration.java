package com.KanbanManagement.KanbanmanagementService;

//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskmanagementApplicationService;
import com.KanbanManagement.KanbanmanagementService.Factories.TaskFactory;
import com.KanbanManagement.KanbanmanagementService.Repositories.JdbcTaskEntityRepository;
import com.KanbanManagement.KanbanmanagementService.Repositories.TaskRepository;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 TaskmanagementApplicationService taskmanagementApplicationService(TaskRepository taskRepository) {
	        return new TaskmanagementApplicationService(taskRepository);
	 }
	 
	 @Bean
	 TaskRepository taskRepository(JdbcTaskEntityRepository jdbcTaskEntityRepository) {
	        return new TaskRepository(jdbcTaskEntityRepository);
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