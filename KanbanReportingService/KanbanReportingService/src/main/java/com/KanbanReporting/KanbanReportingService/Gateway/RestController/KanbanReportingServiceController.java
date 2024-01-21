package com.KanbanReporting.KanbanReportingService.Gateway.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.NotificationReceiverService;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.TaskReportApplicationService;

@RestController
@RequestMapping("/kanbanboard_reporting")
public class KanbanReportingServiceController {
	NotificationReceiverService notificationReceiverService;
	TaskReportApplicationService taskReportApplicationService;
	
	public KanbanReportingServiceController(NotificationReceiverService notificationReceiverService, TaskReportApplicationService taskReportApplicationService) {
		this.notificationReceiverService = notificationReceiverService;
		notificationReceiverService.ReceiveTaskChangedNotification();
		this.taskReportApplicationService = taskReportApplicationService;
	}
	
	@GetMapping("/taskreport/{id}")
	public ResponseEntity<Object> GetTaskReportById(@PathVariable int id) {
		System.out.println("Get Task Report data for Id " + id);
		return taskReportApplicationService.HandleGetTaskReportById(id);
	}
	
	@GetMapping("/taskreport/all_from_board/{id}")
	public ResponseEntity<Object> GetTaskReportForAllTasksOfaBoard(@PathVariable int id) {
		System.out.println("Get Taskreport for all Tasks of Kanbanboard " + id);
		return taskReportApplicationService.HandleGetTaskReportForAllTasksOfaBoard(id);
	}
	
	@GetMapping("/kanbanboardreport/{id}")
	public ResponseEntity<Object> GetKanbanReport(@PathVariable(required = false) int id) {		
		System.out.println("Get Kanbanreport for KanbanboardId " + id);
		return taskReportApplicationService.HandleGetKanbanReport(id);
	}
	
	@GetMapping("/kanbanboardreport/history/{id}")
	public ResponseEntity<Object> GetKanbanReportHistoryForBoardId(@PathVariable(required = false) int id) {		
		System.out.println("Get all Kanbanreports for KanbanboardId " + id);
		return taskReportApplicationService.HandleKanbanReportHistoryForBoard(id);
	}
}
