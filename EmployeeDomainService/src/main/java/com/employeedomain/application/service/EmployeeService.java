package com.employeedomain.application.service;

import java.util.List;

import com.employeedomain.application.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto saveEmployee(EmployeeDto employeeDetails,List<Integer> employeeIds);
	EmployeeDto getEmployee(int empId);
	EmployeeDto updateEmployee(EmployeeDto employeeDetails,int empId);
	List<EmployeeDto> findAllEmployee();

	

}
