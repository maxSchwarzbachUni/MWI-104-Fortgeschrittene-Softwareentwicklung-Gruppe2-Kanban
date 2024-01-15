package com.KanbanManagement.KanbanmanagementService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskManagementKonstanten;
import com.KanbanManagement.KanbanmanagementService.ApplicationServices.TaskmanagementApplicationService;
import com.KanbanManagement.KanbanmanagementService.Repositories.TaskRepository;
import com.KanbanManagement.KanbanmanagementService.ValueObjects.TaskId;

//In Anlehnung an: https://www.tutorialspoint.com/spring_boot/spring_boot_unit_test_cases.htm
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskmanagementDomainServiceTests {
	@Autowired
	private TaskmanagementApplicationService taskmanagementApplicationService;
	
	@Autowired
	private TaskRepository taskRepository;
	
   @Test
   public void when_incorrect_user_id_is_submitted_no_update_will_be_excecuted() {
      Mockito.when(taskRepository.findById(new TaskId(1337))).thenReturn(null);
      String resultMessage = taskmanagementApplicationService.HandleUpdateAssignedStage(1337, 3);
      Assert.assertEquals(TaskManagementKonstanten.task_update_failed_no_task_for_update_found , resultMessage);
   }	
   
   @Test
   public void when_incorrect_user_id_is_submitted_no_update_will_be_excecuted_2() {
      Mockito.when(taskRepository.findById(new TaskId(1337))).thenReturn(null);
      String resultMessage = taskmanagementApplicationService.HandleUpdateAssignedStage(1337, 3);
      Assert.assertEquals("bla bla" , resultMessage);
   }
}
