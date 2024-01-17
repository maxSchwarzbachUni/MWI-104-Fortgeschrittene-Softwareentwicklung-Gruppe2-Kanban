package com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class NotificationReceiverService {
	
	private static final String EXCHANGE_NAME = "task_update_exchange";
	ObjectMapper objectmapper;
	TaskReportDomainService taskReportDomainService;
	
	public NotificationReceiverService(TaskReportDomainService taskReportDomainService) {
		objectmapper = new ObjectMapper();
		this.taskReportDomainService = taskReportDomainService;
	}
	
	public void ReceiveTaskChangedNotificationRabbitMq() {
        // https://www.rabbitmq.com/tutorials/tutorial-five-go.html#:~:text=The%20messages%20will%20be%20sent,%22.
        
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
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
                
             
            
                MessageTaskReportDto messageTaskReportData = objectmapper.readValue(message, MessageTaskReportDto.class);
                taskReportDomainService.importMessageTaskReportData(messageTaskReportData);
                
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }	
        catch(Exception e) {
            System.out.println("Fehler: "+ e);
        }
    }
}