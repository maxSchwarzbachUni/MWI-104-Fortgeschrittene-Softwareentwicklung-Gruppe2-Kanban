package com.KanbanReporting.KanbanReportingService.Gateway.BeanConfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.NotificationReceiverService;

@Configuration
public class BeanConfiguration {
	 @Bean 
	 NotificationReceiverService notificationReceiverService(TaskReportDomainService taskReportDomainService) {
		 return new NotificationReceiverService(taskReportDomainService);
	 }
	 
	 @Bean 
	 TaskReportDomainService taskReportDomainService() {
		 return new TaskReportDomainService();
	 }
}
