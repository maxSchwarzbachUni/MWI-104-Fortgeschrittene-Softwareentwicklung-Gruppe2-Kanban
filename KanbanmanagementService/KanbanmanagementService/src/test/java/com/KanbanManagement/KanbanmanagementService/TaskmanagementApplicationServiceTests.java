package com.KanbanManagement.KanbanmanagementService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.DomainResult;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;
import com.KanbanManagement.KanbanmanagementService.Gateway.MessageServices.CommunicationType;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.IStageRepository;
import com.KanbanManagement.KanbanmanagementService.Gateway.Repositories.ITaskRepository;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskManagementKonstanten;
import com.KanbanManagement.KanbanmanagementService.UseCase.ApplicationServices.TaskmanagementApplicationService;

// In Anlehnung an: https://www.tutorialspoint.com/spring_boot/spring_boot_unit_test_cases.htm
// Auf komplexte Testfälle wurde verzichtet, da das Coding keine möglichen Testfälle bietet hierfür.
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskmanagementApplicationServiceTests {
	@Autowired
	private TaskmanagementApplicationService taskmanagementApplicationService;
	
	@Autowired
	private ITaskRepository taskRepository;
	
//  TESTING HandleUpdateAssignedStage
	
   @Test
   public void when_incorrect_task_id_is_submitted_no_update_will_be_excecuted() {
      Mockito.when(taskRepository.findById(new TaskId(1337))).thenReturn(null);
      String resultMessage = taskmanagementApplicationService.HandleUpdateAssignedStage(1337, 3, CommunicationType.rabbitMQ).getBody().toString();
      Assert.assertEquals(TaskManagementKonstanten.task_update_failed_no_task_for_update_found , resultMessage);
   }	
   
   //test should fail to show that the test above is actually testing something (so its not a placeholder unit test)
   @Test
   public void when_incorrect_task_id_is_submitted_no_update_will_be_excecuted_2() {
      Mockito.when(taskRepository.findById(new TaskId(1337))).thenReturn(null);
      String resultMessage = taskmanagementApplicationService.HandleUpdateAssignedStage(1337, 3, CommunicationType.rabbitMQ).getBody().toString();
      Assert.assertEquals("bla bla" , resultMessage);
   }
}
