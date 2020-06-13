package com.example.demo.constroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aggreagtor.EmployeeViewServiceAggregator;
import com.example.demo.model.EmployeeViewRequest;

@RestController
@RequestMapping("/employee/employeeDetails")
public class EmployeeViewController {


	@Autowired
	EmployeeViewServiceAggregator employeeViewServiceAggregator;
	
	@PostMapping(value="/store",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setEmployeeDetailsRequest(@Valid @RequestBody EmployeeViewRequest employeeViewRequest,BindingResult binding){
		ResponseEntity<String> response=employeeViewServiceAggregator.setEmployeeDetails(employeeViewRequest);
		return response;
		
	}
}
