package com.employeedomain.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeedomain.application.contants.EmployeeConstants;
import com.employeedomain.application.dao.EmployeeRepository;
import com.employeedomain.application.dto.EmployeeDto;
import com.employeedomain.application.entity.EmployeePersonalDetails;
import com.employeedomain.application.entity.EmployeeProfessionalDetail;
import com.employeedomain.application.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private  List<EmployeeDto> emplList=new ArrayList<>();
	
	 
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
      this.employeeRepository=employeeRepository;
	}
	

	private String assignRole(EmployeeDto employeeDetails){
		Integer experience=Integer.parseInt(employeeDetails.getExp());
		if(experience<1)
			return "Trainee";
		else if(experience>=1 && experience<4)
			return "system engineer";
		else if(experience>=4 && experience<7)
			return "Analyst";
		else
			return "Architect";
			
		
	}
	
	private String assignProject(EmployeeDto employeeDetails) {
		if(Integer.parseInt(employeeDetails.getExp())>=5)
			return EmployeeConstants.PROJECTS[0];
		else if(Integer.parseInt(employeeDetails.getExp())<5 && (Integer.parseInt(employeeDetails.getExp())>=3))
			return EmployeeConstants.PROJECTS[1];
		else if(Integer.parseInt(employeeDetails.getExp())<3 && (Integer.parseInt(employeeDetails.getExp())>=2))
		 	return EmployeeConstants.PROJECTS[2];
		else 
			return EmployeeConstants.PROJECTS[3];
	}

	
	@Override
	public EmployeeDto getEmployee(int empId) {
		EmployeePersonalDetails employeeDetails=employeeRepository.findByEmpId(empId);
        if(employeeDetails!=null) {
        	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(employeeDetails);
        	empReturnDto.setProjectName(employeeDetails.getEmpDetails().getProjectName());
        	empReturnDto.setEmpRole(employeeDetails.getEmpDetails().getEmpRole());
        	return empReturnDto;
        }
	
		return null;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDetails,int empId) {
		EmployeePersonalDetails employeePersonalDetails=employeeRepository.findById(empId).get();
		EmployeeProfessionalDetail details=employeePersonalDetails.getEmpDetails();
		employeePersonalDetails.setEmpployeeName(employeeDetails.getEmpName());
		employeePersonalDetails.setAddress(employeeDetails.getAddress());
		employeePersonalDetails.setPhone(employeeDetails.getPhone());
		employeeRepository.save(employeePersonalDetails);
    	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(employeePersonalDetails);
    	empReturnDto.setProjectName(details.getProjectName());
    	empReturnDto.setEmpRole(details.getEmpRole());
        return empReturnDto;
	}
	
	// Method to get all employee details
	 

	@Override
	public List<EmployeeDto> findAllEmployee() {
      Iterable<EmployeePersonalDetails> employeeIterator=employeeRepository.findAll();
      List<EmployeePersonalDetails> employees=EmployeeUtil.iterableToListConverter(employeeIterator);
      List<EmployeeDto> employeesDto=mapEntityToDto(employees);
		return employeesDto;
	}
	
	private List<EmployeeDto> mapEntityToDto( List<EmployeePersonalDetails> empEntity){
		for(EmployeePersonalDetails empE:empEntity) {
	    	EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(empE);
			EmployeeProfessionalDetail details=empE.getEmpDetails();
			empReturnDto.setProjectName(details.getProjectName());
	    	empReturnDto.setEmpRole(details.getEmpRole());
	    	emplList.add(empReturnDto);
		}
		return emplList;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDetails) {
		EmployeePersonalDetails empEntity=(EmployeePersonalDetails)EmployeeUtil.modelMapping(employeeDetails);
		EmployeeDto empReturnDto=(EmployeeDto)EmployeeUtil.modelMapping(empEntity);
        String projectAssigned=assignProject(employeeDetails);
        empReturnDto.setProjectName(projectAssigned);
        String role=assignRole(employeeDetails);
        empReturnDto.setEmpRole(role);
        EmployeeProfessionalDetail empDetails=new EmployeeProfessionalDetail(role,employeeDetails.getExp(),projectAssigned);
        empEntity.setEmpDetails(empDetails);
        employeeRepository.save(empEntity);
        
        return empReturnDto;
	}
	

}
