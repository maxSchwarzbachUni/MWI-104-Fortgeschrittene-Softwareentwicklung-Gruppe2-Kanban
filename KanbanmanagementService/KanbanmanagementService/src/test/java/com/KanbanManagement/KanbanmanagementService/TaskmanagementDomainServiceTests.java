package com.KanbanManagement.KanbanmanagementService;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.KanbanManagement.KanbanmanagementService.Domain.Aggregates.Task;
import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.DomainResult;
import com.KanbanManagement.KanbanmanagementService.Domain.DomainServices.TaskmanagementDomainService;
import com.KanbanManagement.KanbanmanagementService.Domain.Entities.TaskType;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.StageId;
import com.KanbanManagement.KanbanmanagementService.Domain.ValueObjects.TaskId;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskmanagementDomainServiceTests {
	// In Anlehnung an: https://www.tutorialspoint.com/spring_boot/spring_boot_unit_test_cases.htm
	// Auf komplexte Testfälle wurde verzichtet, da das Coding keine möglichen Testfälle bietet hierfür.
	@Autowired
	private TaskmanagementDomainService taskmanagementDomainService;
		
	// TESTING validateNewTaskData
	   
   @Test
   public void when_description_and_workload_are_valid_return_http_ok() {
	   Task testTask = createTestTask(1, "test");
	   DomainResult testDomainResult = taskmanagementDomainService.validateNewTaskData(testTask);
       Assert.assertEquals(HttpStatus.OK, testDomainResult.statusCode);
   }
   
   @Test
   public void when_description_and_workload_are_valid_return_succesful_true() {
	   Task testTask = createTestTask(1, "test");
	   DomainResult testDomainResult = taskmanagementDomainService.validateNewTaskData(testTask);
       Assert.assertEquals(true, testDomainResult.ActionSuccesful);
   }
   
   @Test
   public void when_description_is_too_long_result_will_be_invalid() {
	   Task testTask = createTestTask(1, generateTestStringWithLengthFilledWithCharacter(256, 'a')); 
	   DomainResult testDomainResult = taskmanagementDomainService.validateNewTaskData(testTask);
       Assert.assertEquals(false, testDomainResult.ActionSuccesful);
       Assert.assertEquals("Description Feld ist nicht gültig, da zu lang (Limit 255 Zeichen)", testDomainResult.Message);
   }
   
   @Test
   public void when_workload_is_invalid_return_invalid_result() {
	   Task testTask = createTestTask(0, "test"); // Workload must be greater than 0 to be valid 
	   DomainResult testDomainResult = taskmanagementDomainService.validateNewTaskData(testTask);
       Assert.assertEquals(false, testDomainResult.ActionSuccesful);
       Assert.assertEquals("RemainingWorkload Feld ist keine gültige Zahl (sie muss größer 0 sein)", testDomainResult.Message);
   }
   
   // https://stackoverflow.com/a/1802944 for generating a string with a specific length
   private String generateTestStringWithLengthFilledWithCharacter(int length, char character) {
	   if(length < 0) {
		   return "";
	   }
	   char[] resultArray = new char[length];
	   Arrays.fill(resultArray, character);
	   return new String(resultArray);
   }
   
   private Task createTestTask(double remainingWorkload, String description) {
	   return new Task(new TaskId(1), "test", new StageId(0), description, remainingWorkload, null, TaskType.BugFix , null, (byte)1);
   }
}
