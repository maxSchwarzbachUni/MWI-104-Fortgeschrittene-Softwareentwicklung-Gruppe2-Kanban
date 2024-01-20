package com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.KanbanReporting.KanbanReportingService.Domain.DomainServices.TaskReportDomainService;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.KanbanDashboardEntity;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.IKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.ITaskReportDataRepository;

public class TaskReportApplicationService {
	TaskReportDomainService taskReportDomainService;
	IKanbanDashboardRepository kanbanDashboardRepository;
	ITaskReportDataRepository taskReportDataRepository;
	
	public TaskReportApplicationService(TaskReportDomainService taskReportDomainService, IKanbanDashboardRepository kanbanDashboardRepository, ITaskReportDataRepository taskReportDataRepository) {
		this.taskReportDomainService = taskReportDomainService;
		this.kanbanDashboardRepository = kanbanDashboardRepository;
		this.taskReportDataRepository = taskReportDataRepository;
	}

	public ResponseEntity<Object> GetTaskReportById(int id) {
		try {
			taskReportDomainService.checkIdForValidity(id);
			TaskReportEntity taskReportEntity = taskReportDataRepository.getTaskReportById(id);
			if(taskReportEntity != null) {
				return new ResponseEntity<Object>(taskReportDomainService.convertTaskReportEntityToAggregate(taskReportEntity), HttpStatus.OK);
			}			
			return new ResponseEntity<Object>("No Taskreport data found for id " + id, HttpStatus.NOT_FOUND);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>("Exception! Message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> GetTaskReportForAllTasksOfaBoard(int id) {
		try {
			taskReportDomainService.checkIdForValidity(id);
			Iterable<TaskReportEntity> taskReportEntities =  taskReportDataRepository.getAllTasksReportsOfKanbanId(id);
			if(taskReportEntities != null) {
				return new ResponseEntity<Object>(taskReportDomainService.convertTaskReportEntityListToAggregate(taskReportEntities), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("No Taskreport data found for kanbanId " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Exception! Message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Object> GetKanbanReport(int id) {
		try {
			taskReportDomainService.checkIdForValidity(id);
			KanbanDashboardEntity kanbanDashboardEntity = kanbanDashboardRepository.getKanbanReportForId(id);
			if(kanbanDashboardEntity != null) {
				return new ResponseEntity<Object>(taskReportDomainService.convertKanbanDashboardEntityToAggregate(kanbanDashboardEntity), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("No Kanbanreport data found for kanbanId " + id, HttpStatus.NOT_FOUND);			
		} catch (Exception e) {
			return new ResponseEntity<Object>("Exception! Message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	public ResponseEntity<Object> GetTaskReportsForStageById(int stageId) {
//		try {
//			taskReportDomainService.checkIdForValidity(stageId);
//			Iterable<TaskReportEntity> taskReportEntities = taskReportDataRepository.getTaskReportEntitiesForStage(stageId);
//			if(taskReportEntities != null) {
//				return new ResponseEntity<Object>(taskReportDomainService.convertTaskReportEntityListToAggregate(taskReportEntities), HttpStatus.OK);
//			}
//			return new ResponseEntity<Object>("No Taskreport data found for stageId " + stageId, HttpStatus.NOT_FOUND);
//		} catch (Exception e) {
//			return new ResponseEntity<Object>("Exception! Message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}