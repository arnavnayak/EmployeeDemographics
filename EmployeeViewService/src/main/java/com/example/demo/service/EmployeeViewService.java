package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.EmployeeDomainRequest;
import com.example.demo.model.EmployeeDomainUpdateRequest;
import com.example.demo.model.EmployeeViewRequest;
import com.example.demo.model.EmployeeViewUpdateRequest;

@Service
public class EmployeeViewService {
	
	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String> doEmployeeDomainServiceCall(String domainServiceUrl, HttpMethod post,
			HttpEntity<EmployeeDomainRequest> entity, Class<String> employeeDomainResponse) {
		ResponseEntity<String> responseFromDomainService=restTemplate.exchange(domainServiceUrl, post, entity, employeeDomainResponse);
		return responseFromDomainService;
	}

	public ResponseEntity<String> doEmployeeUpdateServiceCall(String domainServiceUrl, HttpMethod put,
			HttpEntity<EmployeeDomainUpdateRequest> entity, Class<String> employeeDomainResponse) {
		ResponseEntity<String> responseFromDomainService=restTemplate.exchange(domainServiceUrl, put, entity, employeeDomainResponse);
		return responseFromDomainService;
	}

	public ResponseEntity<String> doEmployeeFetchEmployeeServiceCall(String finalDomainServiceUrl, HttpMethod get,HttpEntity<?> requestEntity, Class<String> employeeDomainResponse) {
		ResponseEntity<String> responseFromDomainService=restTemplate.exchange(finalDomainServiceUrl, get, requestEntity, employeeDomainResponse);
		return responseFromDomainService;
	}

	public ResponseEntity<String> doAllEmployeeFetchEmployeeServiceCall(String domainServiceUrl, HttpMethod get,
			HttpEntity<?> entity, Class<String> employeeDomainResponse) {
		ResponseEntity<String> responseFromDomainService=restTemplate.exchange(domainServiceUrl, get, entity, employeeDomainResponse);
		return responseFromDomainService;
	}

}
