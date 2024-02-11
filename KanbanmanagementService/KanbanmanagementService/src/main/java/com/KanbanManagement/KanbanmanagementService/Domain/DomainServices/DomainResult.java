package com.KanbanManagement.KanbanmanagementService.Domain.DomainServices;

import org.springframework.http.HttpStatus;

public class DomainResult {
	public Boolean ActionSuccesful;
	public String Message;
	public HttpStatus statusCode;
	
	public DomainResult(Boolean actionSuccesful, String message, HttpStatus statusCode) {
		super();
		ActionSuccesful = actionSuccesful;
		Message = message;
		this.statusCode = statusCode;
	}	
}
