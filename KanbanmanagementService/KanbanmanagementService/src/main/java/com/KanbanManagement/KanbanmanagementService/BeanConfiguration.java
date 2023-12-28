package com.KanbanManagement.KanbanmanagementService;

//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskmanagementApplicationService;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 TaskmanagementApplicationService taskmanagementApplicationService() {
	        return new TaskmanagementApplicationService();
	 }
	 
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