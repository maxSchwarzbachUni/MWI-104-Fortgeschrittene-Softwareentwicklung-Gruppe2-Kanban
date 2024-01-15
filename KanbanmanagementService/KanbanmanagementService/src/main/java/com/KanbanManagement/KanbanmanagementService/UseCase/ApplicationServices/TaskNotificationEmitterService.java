package com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TaskNotificationEmitterService {

	private static final String EXCHANGE_NAME = "task_update_exchange";
	
	public void EmitTaskChangedNotificationRabbitMq(TaskReportData messageTaskObject) {
		// https://www.rabbitmq.com/tutorials/tutorial-five-go.html#:~:text=The%20messages%20will%20be%20sent,%22.
		
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
	        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

	        String routingKey = "task.update." + messageTaskObject.getTaskId().getId();
	        String message = convertObjectToJson(messageTaskObject);

	        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
	        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
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


