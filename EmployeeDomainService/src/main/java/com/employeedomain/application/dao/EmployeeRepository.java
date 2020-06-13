package com.employeedomain.application.dao;

import org.springframework.data.repository.CrudRepository;

import com.employeedomain.application.entity.EmployeePersonalDetails;

public interface EmployeeRepository extends CrudRepository<EmployeePersonalDetails, Integer> {
	EmployeePersonalDetails findByEmpId(int empId);
	

}

