package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeViewRequest {

	@NotBlank(message="Employee name is required")
	private String empFirstName;
	@NotBlank(message="Employee name is required")
	private String empLastName;
	private String address;
	private String phone;
	@NotBlank(message="Date of joining is required")
	private String dateOfJoining;
	private String techStack;

}
