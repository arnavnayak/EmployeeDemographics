package com.example.demo.aggreagtor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.model.EmployeeDomainRequest;
import com.example.demo.model.EmployeeDomainUpdateRequest;
import com.example.demo.model.EmployeeViewRequest;
import com.example.demo.model.EmployeeViewUpdateRequest;
import com.example.demo.service.EmployeeViewService;

@Component
public class EmployeeViewServiceAggregator {

	@Autowired
	EmployeeViewService employeeViewService;
	
	
	@Autowired
	LoadBalancerClient ribbonLoadBalancerClient;
	@Value("${domainService.createEmployee.name}")
	String domainServiceName;
	@Value("${domainService.createEmployee.url}")
	String domainServiceEndpoint;
	@Value("${domainService.updateEmployee.url}")
	String domainUpdateServiceEndpoint;
	@Value("${domainService.getEmployee.url}")
	String domainViewServiceEndpoint;
	@Value("${domainService.getAllEmployees.url}")
	String domainViewAllServiceEndpoint;
	public ResponseEntity<String> setEmployeeDetails(EmployeeViewRequest employeeViewrequest){
		EmployeeDomainRequest employeeDomainRequest = creatingDomainServiceRequest(employeeViewrequest);
		String domainServiceUrl=createDomainServiceUrl(domainServiceName,domainServiceEndpoint);
		HttpEntity<EmployeeDomainRequest> entity=new HttpEntity<>(employeeDomainRequest); 
		
		return employeeViewService.doEmployeeDomainServiceCall(domainServiceUrl,HttpMethod.POST,entity,String.class);
		
	}

	private EmployeeDomainRequest creatingDomainServiceRequest(EmployeeViewRequest employeeViewrequest) {
		EmployeeDomainRequest employeeDomainRequest=new EmployeeDomainRequest();
		employeeDomainRequest.setEmpName(employeeViewrequest.getEmpFirstName()+" "+employeeViewrequest.getEmpLastName());
		employeeDomainRequest.setAddress(employeeViewrequest.getAddress());
		employeeDomainRequest.setPhone(employeeViewrequest.getPhone());
		employeeDomainRequest.setEmail(employeeViewrequest.getEmpFirstName().toLowerCase()+"."+employeeViewrequest.getEmpLastName().toLowerCase().concat("@xyz.com"));
		String doj=employeeViewrequest.getDateOfJoining();
		float experience = totalExperience(doj);
		employeeDomainRequest.setTechStack(employeeViewrequest.getTechStack());
		employeeDomainRequest.setExp(experience);
		return employeeDomainRequest;
	
	}

	private float totalExperience(String doj) {
		LocalDate today = LocalDate.now();
		LocalDate d1 = LocalDate.parse(doj, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate d2 = LocalDate.parse(today.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
		long  diff = ChronoUnit.DAYS.between(d1.atStartOfDay(), d2.atStartOfDay());
		float experience = (diff/360);
		return experience;
	}

	private String createDomainServiceUrl(String serviceName,String serviceEndpoint) {
		String url="";
		ServiceInstance instance=this.ribbonLoadBalancerClient.choose(serviceName);
		if(null!=instance) {
			url=instance.getUri().toString().concat(serviceEndpoint);
		}
		return url;
	}

	public ResponseEntity<String> updateEmployeeDetails(EmployeeViewUpdateRequest employeeViewUpdateRequest) {
		EmployeeDomainUpdateRequest employeeDomainRequest = creatingUpdateServiceRequest(employeeViewUpdateRequest);
		String domainServiceUrl=createDomainServiceUrl(domainServiceName,domainUpdateServiceEndpoint);
		String finalDomainServiceUrl=domainServiceUrl+employeeViewUpdateRequest.getEmpId();
		HttpEntity<EmployeeDomainUpdateRequest> entity=new HttpEntity<>(employeeDomainRequest); 
		
		return employeeViewService.doEmployeeUpdateServiceCall(finalDomainServiceUrl,HttpMethod.PUT,entity,String.class);
		
	}

	private EmployeeDomainUpdateRequest creatingUpdateServiceRequest(EmployeeViewUpdateRequest employeeViewUpdateRequest) {
		EmployeeDomainUpdateRequest employeeDomainRequest=new EmployeeDomainUpdateRequest();
		employeeDomainRequest.setAddress(employeeViewUpdateRequest.getAddress());
		employeeDomainRequest.setPhone(employeeViewUpdateRequest.getPhone());
		employeeDomainRequest.setTechStack(employeeViewUpdateRequest.getTechStack());
		employeeDomainRequest.setExp(employeeViewUpdateRequest.getExp());
		return employeeDomainRequest;
	}

	public ResponseEntity<String> fetchEmployeeDetails(int empId) {
		String domainServiceUrl=createDomainServiceUrl(domainServiceName,domainViewServiceEndpoint);
		String finalDomainServiceUrl=domainServiceUrl+"{"+empId+"}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return employeeViewService.doEmployeeFetchEmployeeServiceCall(finalDomainServiceUrl,HttpMethod.GET,entity,String.class);
		
	}

	public ResponseEntity<String> fetchAllEmployeeDetails() {
		String domainServiceUrl=createDomainServiceUrl(domainServiceName,domainViewAllServiceEndpoint);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return employeeViewService.doAllEmployeeFetchEmployeeServiceCall(domainServiceUrl,HttpMethod.GET,entity,String.class);
	}
	
}
