package com.employeedomain.application.model;

import lombok.Data;

@Data
public class EmployeeResponseModel {
	
	private int empId;
	private String empFirstName;
	private String empLastName;
	private String projectName;
	private String email;
	private String empRole;
	
}
