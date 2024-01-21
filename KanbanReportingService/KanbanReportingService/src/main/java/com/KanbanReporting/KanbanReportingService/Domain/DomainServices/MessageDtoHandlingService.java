package com.KanbanReporting.KanbanReportingService.Domain.DomainServices;

import java.util.ArrayList;
import java.util.List;

import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.KanbanDashboard;
import com.KanbanReporting.KanbanReportingService.Domain.Aggregates.TaskReport;
import com.KanbanReporting.KanbanReportingService.Domain.Entities.TaskReportEntity;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.KanbanDashboardFactory;
import com.KanbanReporting.KanbanReportingService.Domain.Factories.TaskReportFactory;
import com.KanbanReporting.KanbanReportingService.Domain.Helper.DateHelper;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.IKanbanDashboardRepository;
import com.KanbanReporting.KanbanReportingService.Gateway.Repositories.ITaskReportDataRepository;
import com.KanbanReporting.KanbanReportingService.UseCase.ApplicationServices.Dto.MessageTaskReportDto;

public class MessageDtoHandlingService {
	
	ITaskReportDataRepository taskReportDataRepository;
	IKanbanDashboardRepository kanbanDashboardRepository;
	KanbanDashboardFactory kanbanDashboardFactory;
	 TaskReportFactory taskReportFactory;
	
	public MessageDtoHandlingService(ITaskReportDataRepository taskReportDataRepository, IKanbanDashboardRepository kanbanDashboardRepository, TaskReportFactory taskReportFactory, KanbanDashboardFactory kanbanDashboardFactory) {
		this.taskReportDataRepository = taskReportDataRepository;
		this.kanbanDashboardRepository = kanbanDashboardRepository;
		this.taskReportFactory = taskReportFactory;
		this.kanbanDashboardFactory = kanbanDashboardFactory;
	}
	
	public void ImportMessageTaskReportData(MessageTaskReportDto messageTaskReportData) throws Exception {
		TaskReportEntity taskReportById = taskReportDataRepository.getTaskReportById(messageTaskReportData.getTaskId());
		TaskReportEntity taskReportToSave = null;  
		if(taskReportById == null) {
			taskReportToSave = taskReportFactory.ConvertToEntityWithoutId(messageTaskReportData);
		}
		else {
			taskReportFactory.ConvertToAggregate(taskReportById);
			taskReportToSave = NeueTaskReportDataUebernehmen(messageTaskReportData, taskReportById);
		}
		
		TaskReportEntity result = taskReportDataRepository.saveTaskReport(taskReportToSave);
		if(result == null) {
			throw new Exception("Saving new task report unsuccesful for taskid " + taskReportToSave.getTaskId());
		}
		System.out.println("Saving new task report succesful for taskid " + taskReportToSave.getTaskId() + ". Generating new Dashboard.");
		KanbanDashboard kanbanDashboardToSave = GenerateNewKanbanDashbord(messageTaskReportData.getKanbanid());
		kanbanDashboardRepository.saveNewKanbanDashboard(kanbanDashboardFactory.ConvertToEntityWithoutId(kanbanDashboardToSave));
	}

	private TaskReportEntity NeueTaskReportDataUebernehmen(MessageTaskReportDto messageTaskReportData, TaskReportEntity taskReportToSave) {
		taskReportToSave.setCreationDate(messageTaskReportData.getCreationDate());
		taskReportToSave.setKanbanboardId(messageTaskReportData.getKanbanid());	
		taskReportToSave.setLastChange(messageTaskReportData.getLastChange());
		taskReportToSave.setTaskId(messageTaskReportData.getTaskId());
		if(messageTaskReportData.getUpdateToLastStage()) {
			taskReportToSave.setClosedDate(messageTaskReportData.getLastChange());
		}		
		else {
			taskReportToSave.setClosedDate(null);
		}
		
		return taskReportToSave;
	}
	
	private KanbanDashboard GenerateNewKanbanDashbord(int kanbanId) throws Exception {
		KanbanDashboard resultDashboard = new KanbanDashboard();
		resultDashboard.setKanbanId(kanbanId);
		resultDashboard.setDashboardCreationDate(DateHelper.getCurrentTime());
		
		List<TaskReport> taskReportList = getTaskReportListForId(kanbanId);		
		
		resultDashboard.setAvgLeadTime(CalculateAvgLeadTimeInHours(taskReportList));
		resultDashboard.setAvgIdleTime(CalculateAvgIdleTime(taskReportList)); 
		resultDashboard.setAvgUnfinishedProcessingTimeInHours(CalculateAvgUnfinishedProcessingTime(taskReportList));
		TaskReport oldestActiveTask = DetermineOldestActiveTask(taskReportList);
		resultDashboard.setOldestActiveTask(oldestActiveTask.getTaskId());
		resultDashboard.setOldestActiveTaskTimeInHours(oldestActiveTask.getUnfinishedProcessingTimeInHours());
		return resultDashboard;
	}

	private List<TaskReport> getTaskReportListForId(int kanbanId) throws Exception {
		Iterable<TaskReportEntity> taskReportEntityList = taskReportDataRepository.getAllTasksReportsOfKanbanId(kanbanId);
		List<TaskReport> taskReportList = convertTaskReportEntityListToAggregateList(taskReportEntityList);
		if(taskReportList.isEmpty()) {
			throw new Exception("No taskreport data found.");
		}
		return taskReportList;
	}

	private TaskReport DetermineOldestActiveTask(List<TaskReport> taskReportList) throws Exception {
		TaskReport longestActiveTask = taskReportList.get(0);	
		for (TaskReport taskReport : taskReportList) {
			double unfinishedProcessingTimeInHours = taskReport.getUnfinishedProcessingTimeInHours();
			double currentLongestunfinishedProcessingTimeInHours = longestActiveTask.getUnfinishedProcessingTimeInHours();
			if(unfinishedProcessingTimeInHours > currentLongestunfinishedProcessingTimeInHours) {
				longestActiveTask = taskReport;
			}
		}
		System.out.println("Found oldest active task with id " + longestActiveTask.getTaskId() + " with a active time of " + longestActiveTask.getUnfinishedProcessingTimeInHours() + " hours open.");
		
		return longestActiveTask;		
	}


	private double CalculateAvgUnfinishedProcessingTime(List<TaskReport> taskReportList) {
		double avgUnfinishedProcessingTimeSum = 0.0;
		for (TaskReport taskReport : taskReportList) {
			if(taskReport.getUnfinishedProcessingTimeInHours() > 0) {
				avgUnfinishedProcessingTimeSum += taskReport.getUnfinishedProcessingTimeInHours();
			}
		}		
		double avgUnfinishedProcessingTime = avgUnfinishedProcessingTimeSum / taskReportList.size();
		System.out.println("Calculated avg unfinished processing time of " + avgUnfinishedProcessingTime + " hours.");
		
		return avgUnfinishedProcessingTime;			
	}

	private double CalculateAvgIdleTime(List<TaskReport> taskReportList) {
		double avgIdleTimeSum = 0.0;
		for (TaskReport taskReport : taskReportList) {
			if(taskReport.getIdleTimeInHours() > 0) {
				avgIdleTimeSum += taskReport.getIdleTimeInHours();
			}
		}		
		double avgIdleTime = avgIdleTimeSum / taskReportList.size();
		System.out.println("Calculated avg idle time of " + avgIdleTime + " hours.");
		return avgIdleTime;		
	}

	private double CalculateAvgLeadTimeInHours(List<TaskReport> taskReportList) {
		double leadTimeSum = 0.0;
		for (TaskReport taskReport : taskReportList) {
			if(taskReport.getLeadTimeInHours() > 0) {
				leadTimeSum += taskReport.getLeadTimeInHours();
			}
		}		
		double avgLeadTime = leadTimeSum / taskReportList.size();
		System.out.println("Calculated avg lead time of " + avgLeadTime + " hours.");
		
		return avgLeadTime;
	}

	private List<TaskReport> convertTaskReportEntityListToAggregateList(Iterable<TaskReportEntity> taskReportEntityList) {
		List<TaskReport> taskReportList = new ArrayList<TaskReport>();
		for (TaskReportEntity taskReportEntity : taskReportEntityList) {
			taskReportList.add(taskReportFactory.ConvertToAggregate(taskReportEntity));
		}
		return taskReportList;
	}
}
