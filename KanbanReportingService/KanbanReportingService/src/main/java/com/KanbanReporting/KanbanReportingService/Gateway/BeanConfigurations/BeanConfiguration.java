package com.KanbanReporting.KanbanReportingService.Gateway.BeanConfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.KanbanDashboardFactory;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.TaskReportFactory;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.IKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.ITaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcTaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.KanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.TaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.NotificationReceiverService;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.TaskReportApplicationService;

@Configuration
public class BeanConfiguration {
	 @Bean 
	 NotificationReceiverService notificationReceiverService(TaskReportDomainService taskReportDomainService) {
		 return new NotificationReceiverService(taskReportDomainService);
	 }
	 
	 @Bean 
	 TaskReportDomainService taskReportDomainService() {
		 TaskReportFactory taskReportFactory = new TaskReportFactory();
		KanbanDashboardFactory kanbanDashboardFactory = new KanbanDashboardFactory();
		return new TaskReportDomainService(taskReportFactory, kanbanDashboardFactory);
	 }
	 
	 @Bean
	 TaskReportApplicationService taskReportApplicationService(TaskReportDomainService taskReportDomainService, ITaskReportDataRepository taskReportDataRepository, IKanbanDashboardRepository kanbanDashboardRepository) {
		 return new TaskReportApplicationService(taskReportDomainService, kanbanDashboardRepository, taskReportDataRepository);
	 }
	 
	 @Bean
	 ITaskReportDataRepository iTaskReportDataRepository(JdbcTaskReportDataRepository jdbcTaskReportDataRepository) {
	        return new TaskReportDataRepository(jdbcTaskReportDataRepository);
	 }
	 
	 @Bean
	 IKanbanDashboardRepository iKanbanDashboardRepository(JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository) {
	        return new KanbanDashboardRepository(jdbcKanbanDashboardRepository);
	 }
}
