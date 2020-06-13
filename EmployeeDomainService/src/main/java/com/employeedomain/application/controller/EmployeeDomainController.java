package com.employeedomain.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeedomain.application.dto.EmployeeDto;
import com.employeedomain.application.model.EmployeeRequestModel;
import com.employeedomain.application.model.EmployeeRequestUpdateModel;
import com.employeedomain.application.model.EmployeeResponseModel;
import com.employeedomain.application.model.EmployeesAll;
import com.employeedomain.application.service.EmployeeService;
import com.employeedomain.application.util.EmployeeUtil;


@RestController
@RequestMapping("/employeedomainService")
public class EmployeeDomainController {
    
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@PostMapping("/setEmployee")
	public ResponseEntity<EmployeeResponseModel> setEmployeeDetails(@Valid @RequestBody EmployeeRequestModel employeeDetails,BindingResult binder) {
		
		//Validaion for email
		List<EmployeeDto> returnEmpDetailList=employeeService.findAllEmployee();
		for(EmployeeDto employee:returnEmpDetailList) {
			if(employee.getEmail().equals(employeeDetails.getEmail())) {
				//ResponseEntity<EmployeeResponseModel> body=new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
				EmployeeResponseModel body=new EmployeeResponseModel();
				body.getEmail();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body) ;
			}
		}
		//Storing temporarily 
		EmployeeDto employeeDto=(EmployeeDto) EmployeeUtil.modelMapping(employeeDetails);
		EmployeeDto returnEmpDetails=employeeService.saveEmployee(employeeDto);
		
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnResult) ;
	}
	
	@PutMapping("/updateEmployee/{empId}")
	public ResponseEntity<EmployeeResponseModel> updateEmployee( @RequestBody EmployeeRequestUpdateModel employeeDetails,BindingResult binder,@PathVariable("empId")int empId) {
		EmployeeDto employeeDto=(EmployeeDto) EmployeeUtil.modelMapping(employeeDetails);
		EmployeeDto returnEmpDetails=employeeService.updateEmployee(employeeDto,empId);
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.OK).body(returnResult) ;
	}
	
	@GetMapping("/getEmployee")
	public ResponseEntity<EmployeeResponseModel>  getEmployee(@RequestParam("empId") int empId) {
		EmployeeDto returnEmpDetails=employeeService.getEmployee(empId);
		EmployeeResponseModel returnResult=EmployeeUtil.modelMappingResponse(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.OK).body(returnResult) ;
    
	}
	
	@GetMapping("/fetchAllEmployeesDetails")
	public ResponseEntity<EmployeesAll>  getAllEmployees() {
		List<EmployeeDto> returnEmpDetails=employeeService.findAllEmployee();
		EmployeesAll allEmployees=EmployeeUtil.setEmpDto(returnEmpDetails);
		return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    
	}
	
	
}
