package com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.TaskReportData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaMessageEmitterService implements IMessageEmitterService {
	@Autowired(required=false)
    private KafkaTemplate<String, String> kafkaTemplate;
	private static final String topic = "task_update";	
    
	@Override
    public void sendMessage(TaskReportData messageTaskObject) {
		try {
			String messageString = convertObjectToJson(messageTaskObject);
			kafkaTemplate.send(topic,messageString);
			System.out.println(" [x] Sent with kafka ' " + messageString);
		} 
		catch (Exception e) {
			System.out.println("Error while sending message with kafka. Message: " + e.getMessage());
		}
    }
    
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
    
    public static String convertObjectToJson(Object object) throws Exception {
        // Erstelle einen ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Konvertiere das Objekt in JSON als String
        return objectMapper.writeValueAsString(object);
    }
}