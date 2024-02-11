package com.KanbanManagement.KanbanmanagementService.Gateway.RestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.StageApplicationService;
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
	public ResponseEntity<Object> GetAlltasks() {
		System.out.println("Get all Tasks");
		return taskmanagementApplicationService.HandleGetAllTasksRequest();
	}
	
	@GetMapping("tasks/{id}")
	public ResponseEntity<Object> GetTaskById(@PathVariable int id) {
		System.out.println("Get Task with id " + id);
		return taskmanagementApplicationService.HandleGetTaskById(id);
	}
	
	@PostMapping("tasks")
	public ResponseEntity<Object> PostNewTask(@RequestBody Task task) {
		System.out.println("Post new task");
		return taskmanagementApplicationService.HandlePostNewTask(task);
	}
	
	@PutMapping("tasks/{id}/stage")
	public ResponseEntity<Object> UpdateAssignedStage(@PathVariable int id, @RequestParam(value = "stageId") int stageId) {
		System.out.println("Update Task " + id + " stage to " + stageId );
		return taskmanagementApplicationService.HandleUpdateAssignedStage(id, stageId);
	}
	
	@PutMapping("tasks/update")
	public ResponseEntity<Object> UpdateTaskField(@RequestParam int id, @RequestParam String fieldname, @RequestParam Object value){
		System.out.println("Update Task " + id + " field " + fieldname + " to " + value);	
		return taskmanagementApplicationService.HandleUpdateTaskField(id, fieldname, value);
	}
	
	@PostMapping("stages")
	public ResponseEntity<Object> PostNewStage(@RequestParam String name, @RequestParam int position, @RequestParam int kanbanid) {
		System.out.println("Post new stage with name " + name + " and position " + position + " to board " + kanbanid);
		return stagemanagementApplicationService.HandlePostNewStage(name, position, kanbanid);
	}
	
	@GetMapping("stages")
	public ResponseEntity<Object> GetAllStages() {
		System.out.println("Get all Stages");
		return stagemanagementApplicationService.HandleGetAllStages();
	}
	
	@GetMapping("stages/{id}")
	public ResponseEntity<Object> GetStageById(@PathVariable int id) {
		System.out.println("Get Stage with id " + id);
		return stagemanagementApplicationService.HandleGetStageById(id);
	}
	
	@GetMapping("raiseNotification")
	public ResponseEntity<Object> raiseRabbitMqNotificiation() {
//		TaskChangedNotificationEmitterService taskChangedNotificationService = new TaskChangedNotificationEmitterService();
//		taskChangedNotificationService.EmitTaskChangedNotificationRabbitMq("Test");
		return new ResponseEntity<Object>("Erfolg.", HttpStatus.OK);
	}
	
}