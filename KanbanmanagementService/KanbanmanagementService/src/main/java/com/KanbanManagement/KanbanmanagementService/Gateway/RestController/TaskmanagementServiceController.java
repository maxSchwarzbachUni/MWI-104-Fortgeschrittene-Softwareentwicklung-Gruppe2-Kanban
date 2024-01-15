package com.KanbanManagement.KanbanmanagementService.Gateway.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Stage;
import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.StageApplicationService;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskNotificationEmitterService;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskmanagementApplicationService;

@RestController
@RequestMapping("/kanbanboard_management")
public class TaskmanagementServiceController {
	TaskmanagementApplicationService taskmanagementApplicationService;
	StageApplicationService stagemanagementApplicationService;
	
	public TaskmanagementServiceController(TaskmanagementApplicationService taskmanagementApplicationService, StageApplicationService stagemanagementApplicationService) {
		super();
		this.taskmanagementApplicationService = taskmanagementApplicationService;
		this.stagemanagementApplicationService = stagemanagementApplicationService;
	}

	@GetMapping("tasks")
	public Task[] GetAlltasks() {
		System.out.println("Get all Tasks");
		return taskmanagementApplicationService.HandleGetAllTasksRequest();
	}
	
	@GetMapping("tasks/{id}")
	public Task GetTaskById(@PathVariable int id) {
		System.out.println("Get Task with id " + id);
		return taskmanagementApplicationService.HandleGetTaskById(id);
	}
	
	@PostMapping("tasks")
	public String PostNewTask() {
		System.out.println("Post new task");
		return "Post new task";
	}
	
	@GetMapping("taskstest/{id}")
	public Task GetTest(@PathVariable int id) {
		return taskmanagementApplicationService.test();
	}
	
	@PutMapping("tasks/{id}/stage")
	public String UpdateAssignedStage(@PathVariable int id, @RequestParam(value = "stageId") int stageId) {
		return taskmanagementApplicationService.HandleUpdateAssignedStage(id, stageId);
		
	}
	
	@PostMapping("stages")
	public Stage PostNewStage(@RequestParam String name, @RequestParam int position) {
		return stagemanagementApplicationService.HandlePostNewStage(name, position);
	}
	
	//TODO entfernen
	@GetMapping("raiseNotification")
	public String raiseRabbitMqNotificiation() {
//		TaskChangedNotificationEmitterService taskChangedNotificationService = new TaskChangedNotificationEmitterService();
//		taskChangedNotificationService.EmitTaskChangedNotificationRabbitMq("Test");
		return "Erfolg.";
	}
	
}