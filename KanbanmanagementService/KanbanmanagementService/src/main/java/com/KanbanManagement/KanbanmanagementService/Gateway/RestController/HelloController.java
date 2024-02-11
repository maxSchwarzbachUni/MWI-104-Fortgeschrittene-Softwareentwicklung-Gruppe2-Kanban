package com.KanbanManagement.KanbanmanagementService.Gateway.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices.KafkaMessageEmitterService;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	// Test for kafka communication
    @Autowired
    private KafkaMessageEmitterService messageProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
    	
        messageProducer.sendMessage("my-topic", message);
        System.out.println("Message sent: " + message);
        return "Message sent: " + message;
    }
}