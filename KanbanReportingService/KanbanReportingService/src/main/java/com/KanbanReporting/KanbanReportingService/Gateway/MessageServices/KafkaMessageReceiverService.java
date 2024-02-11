package com.KanbanReporting.KanbanReportingService.Gateway.MessageServices;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.MessageDtoHandlingService;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.Dto.MessageTaskReportDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;

@Component
public class KafkaMessageReceiverService {

	ObjectMapper objectmapper;
	MessageDtoHandlingService messageDtoHandlingService;	
	private static final String topic = "task_update";	
	
	public KafkaMessageReceiverService(MessageDtoHandlingService messageDtoHandlingService) {
		this.messageDtoHandlingService = messageDtoHandlingService;
		objectmapper = new ObjectMapper();
	}
	
	@KafkaListener(topics = topic, groupId = "taskdata_group")
    public void listen(String message) {
		try {
			System.out.println("Received new message from kafka: '" + message);
			MessageTaskReportDto messageTaskReportData = objectmapper.readValue(message, MessageTaskReportDto.class);
			messageDtoHandlingService.ImportMessageTaskReportData(messageTaskReportData);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}