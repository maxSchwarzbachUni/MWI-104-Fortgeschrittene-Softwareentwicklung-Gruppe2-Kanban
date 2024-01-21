package com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.MessageDtoHandlingService;
import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.Dto.MessageTaskReportDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class NotificationReceiverService {
	
	private static final String EXCHANGE_NAME = "task_update_exchange";
	ObjectMapper objectmapper;
	CommunicationFramework communicationFramework;
	MessageDtoHandlingService messageDtoHandlingService;
	
	public NotificationReceiverService(CommunicationFramework communicationFramework, MessageDtoHandlingService messageDtoHandlingService) {
		this.communicationFramework = communicationFramework;
		this.messageDtoHandlingService = messageDtoHandlingService;
		objectmapper = new ObjectMapper();
	}
	
	public void ReceiveTaskChangedNotification() {
		switch (communicationFramework) {
		case RabbitMQ:  {
			ReceiveTaskChangedNotificationRabbitMq();
			break;
		}
		case Kafka:  {
			ReceiveTaskChangedNotificationKafka();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + communicationFramework);
		}
	}
	
    private void ReceiveTaskChangedNotificationKafka(){
    	// TODO 
	}

	// In Anlehnung an: https://www.rabbitmq.com/tutorials/tutorial-five-go.html#:~:text=The%20messages%20will%20be%20sent,%22.
	public void ReceiveTaskChangedNotificationRabbitMq() {
        try { 
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "task.update.*");
            System.out.println("Waiting for messages.");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                HandleDeliverCallback(delivery);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }	
        catch(Exception e) {
            System.out.println("Fehler: "+ e);
        }
    }

	private void HandleDeliverCallback(Delivery delivery) {
		try {
			String message = new String(delivery.getBody(), "UTF-8");
		    System.out.println("Received new message '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
		    MessageTaskReportDto messageTaskReportData = objectmapper.readValue(message, MessageTaskReportDto.class);
			messageDtoHandlingService.ImportMessageTaskReportData(messageTaskReportData);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}