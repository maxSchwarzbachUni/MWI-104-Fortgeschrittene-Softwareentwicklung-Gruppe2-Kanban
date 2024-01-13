package com.KanbanReporting.KanbanReportingService.Gateway.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.NotificationReceiverService;

@RestController
@RequestMapping("/kanbanboard_reporting")
public class KanbanReportingServiceController {
	
	public KanbanReportingServiceController() {
		NotificationReceiverService service = new NotificationReceiverService();
		service.ReceiveTaskChangedNotificationRabbitMq();
	}
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
  
	@GetMapping("Test")
	public String test() {
		return "das hier ist ein Test";
	}
}
