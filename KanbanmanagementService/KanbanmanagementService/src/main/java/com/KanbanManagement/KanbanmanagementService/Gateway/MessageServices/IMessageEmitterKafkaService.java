package com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;

public interface IMessageEmitterKafkaService {
	public void sendMessage(TaskReportData messageTaskObject);
}
