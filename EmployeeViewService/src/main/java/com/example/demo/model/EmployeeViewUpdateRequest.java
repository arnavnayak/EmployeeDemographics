package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeViewUpdateRequest {
	@NotBlank(message="Employee ID is required for updating")
	private String empId;
	@NotBlank(message="Employee name is required for updating")
	private String empFirstName;
	@NotBlank(message="Employee name is required for updating")
	private String empLastName;
	private String address;
	private String phone;
	@NotBlank(message="exp required for updating")
	private float exp;
	private String techStack;

}
