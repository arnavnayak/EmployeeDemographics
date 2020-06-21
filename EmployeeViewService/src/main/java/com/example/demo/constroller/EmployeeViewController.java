package com.example.demo.constroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aggreagtor.EmployeeViewServiceAggregator;
import com.example.demo.model.EmployeeViewRequest;
import com.example.demo.model.EmployeeViewUpdateRequest;

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
	@PutMapping(value="/update",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployeeDetailsRequest(@Valid @RequestBody EmployeeViewUpdateRequest employeeViewUpdateRequest,BindingResult binding){
		ResponseEntity<String> response=employeeViewServiceAggregator.updateEmployeeDetails(employeeViewUpdateRequest);
		return response;
	}
	@GetMapping(value="/fetchParticularDetail/{empId}",produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchParticularEmployeeDetail(@PathVariable("empId")int empId ){
		ResponseEntity<String> response=employeeViewServiceAggregator.fetchEmployeeDetails(empId);
		return response;
	}
	@GetMapping(value="/fetchAllDetail",produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchAllEmployeeDetail(){
		ResponseEntity<String> response=employeeViewServiceAggregator.fetchAllEmployeeDetails();
		return response;
	}
}
