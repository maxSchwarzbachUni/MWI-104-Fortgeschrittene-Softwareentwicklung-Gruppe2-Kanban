package com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;

public class TaskNotificationEmitterService {
	RabbitMqMessageEmitterService rabbitMqMessageEmitterService;
	KafkaMessageEmitterService kafkaMessageEmitterService;
	
	public TaskNotificationEmitterService(RabbitMqMessageEmitterService rabbitMqMessageEmitterService, KafkaMessageEmitterService kafkaMessageEmitterService) {
		this.kafkaMessageEmitterService = kafkaMessageEmitterService;
		this.rabbitMqMessageEmitterService = rabbitMqMessageEmitterService;
	}
	
	// Switching inbetween communication of RabbitMQ or Kafka, which is controlled by a parameter send by the user sending the rest request.
	public void EmitTaskChangedNotificationRabbitMq(TaskReportData messageTaskObject, CommunicationType communicationType) {
		switch (communicationType) {
		case rabbitMQ: {
			System.out.println("Send message with rabbitMQ");
			rabbitMqMessageEmitterService.sendMessage(messageTaskObject);
			break;
		}
		case kafka: {
			System.out.println("Send message with kafka");
			kafkaMessageEmitterService.sendMessage(messageTaskObject);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + communicationType);
		}
    }
}	