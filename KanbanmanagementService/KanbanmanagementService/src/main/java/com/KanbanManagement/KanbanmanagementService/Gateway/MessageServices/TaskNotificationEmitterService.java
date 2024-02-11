package com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TaskNotificationEmitterService {
	RabbitMqMessageEmitterService rabbitMqMessageEmitterService;
	KafkaMessageEmitterService kafkaMessageEmitterService;
	
	public TaskNotificationEmitterService(RabbitMqMessageEmitterService rabbitMqMessageEmitterService, KafkaMessageEmitterService kafkaMessageEmitterService) {
		this.kafkaMessageEmitterService = kafkaMessageEmitterService;
		this.rabbitMqMessageEmitterService = rabbitMqMessageEmitterService;
	}
	
	
	public void EmitTaskChangedNotificationRabbitMq(TaskReportData messageTaskObject, CommunicationType communicationType) {
		// https://www.rabbitmq.com/tutorials/tutorial-five-go.html#:~:text=The%20messages%20will%20be%20sent,%22.
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