package com.example.demo.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeDomainRequest {

	private String empName;
	private String email;
	private String address;
	private String phone;
	@NotBlank(message="Date of joining is required")
	private String dateOfJoining;
	private String techStack;
	@NotBlank(message="Experience is required")
	@Size(min=1,message="Min 0 years to be entered")
	private float exp;
	
}
