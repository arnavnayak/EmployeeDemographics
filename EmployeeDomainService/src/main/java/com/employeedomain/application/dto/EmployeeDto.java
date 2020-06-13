package com.employeedomain.application.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class EmployeeDto implements Serializable {
	private static final long serialVersionUID = 5262927923207282010L;
	//for first and second tables
	private int empId;
	private String empName;
	private String address;
	private String phone;
	private String dateOfJoining;
	private String techStack;
	private String exp;
	private String projectName;
	private String email;
	private String empRole;
	

}
