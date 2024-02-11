package com.KanbanReporting.KanbanReportingService.Gateway.BeanConfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.MessageDtoHandlingService;
import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.KanbanDashboardFactory;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.TaskReportFactory;
import com.KanbanReporting.KanbanReportingService.Gateway.MessageServices.RabbitMqNotificationReceiverService;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.IKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.ITaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcKanbanDashboardByKanbanIdRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcTaskReportByTaskIdRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.JdbcTaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.KanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.TaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.TaskReportApplicationService;

@Configuration
public class BeanConfiguration {
	 @Bean 
	 RabbitMqNotificationReceiverService rabbitMqNotificationReceiverService(MessageDtoHandlingService messageDtoHandlingService) {
		 return new RabbitMqNotificationReceiverService(messageDtoHandlingService);
	 }
	 
	 @Bean 
	 MessageDtoHandlingService messageDtoHandlingService(ITaskReportDataRepository taskReportDataRepository, IKanbanDashboardRepository kanbanDashboardRepository) {
		 TaskReportFactory taskReportFactory = new TaskReportFactory();
		 KanbanDashboardFactory kanbanDashboardFactory = new KanbanDashboardFactory();
		 return new MessageDtoHandlingService(taskReportDataRepository, kanbanDashboardRepository, taskReportFactory, kanbanDashboardFactory);
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
	 ITaskReportDataRepository iTaskReportDataRepository(JdbcTaskReportDataRepository jdbcTaskReportDataRepository, JdbcTaskReportByTaskIdRepository jdbcTaskReportByTaskIdRepository) {
	     return new TaskReportDataRepository(jdbcTaskReportDataRepository, jdbcTaskReportByTaskIdRepository);
	 }
	 
	 @Bean
	 IKanbanDashboardRepository iKanbanDashboardRepository(JdbcKanbanDashboardRepository jdbcKanbanDashboardRepository, JdbcKanbanDashboardByKanbanIdRepository jdbcKanbanDashboardByKanbanIdRepository) {
	     return new KanbanDashboardRepository(jdbcKanbanDashboardRepository, jdbcKanbanDashboardByKanbanIdRepository);
	 }
}
