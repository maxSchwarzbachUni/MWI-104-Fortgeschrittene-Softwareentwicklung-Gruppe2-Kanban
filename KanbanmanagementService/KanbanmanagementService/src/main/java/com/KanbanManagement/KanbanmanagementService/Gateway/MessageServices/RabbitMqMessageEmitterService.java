package com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// RabbitMQ Coding in Anlehnung an: https://www.rabbitmq.com/tutorials/tutorial-five-java.html
public class RabbitMqMessageEmitterService implements IMessageEmitterRabbitMqService {

	private static final String EXCHANGE_NAME = "task_update_exchange";	
	
	public void sendMessage(TaskReportData messageTaskObject) {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
	        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

	        String routingKey = "task.update." + messageTaskObject.getTaskId();
	        String message = convertObjectToJson(messageTaskObject);

	        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
	        System.out.println(" [x] Sent with rabbitMQ '" + routingKey + "':'" + message + "'");
	    }
	    catch(Exception e) {
	    	System.out.println("Fehler: " + e.getMessage());
	    }
	}
	
    public static String convertObjectToJson(Object object) throws Exception {
        // Erstelle einen ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Konvertiere das Objekt in JSON als String
        return objectMapper.writeValueAsString(object);
    }

}
