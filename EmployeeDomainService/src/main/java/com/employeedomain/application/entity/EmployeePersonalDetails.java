package com.employeedomain.application.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="employeepersonaldetails")
@Data
public class EmployeePersonalDetails implements Serializable {

	
	private static final long serialVersionUID = 4662580024017990646L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="empid")
	private int empId;
	@Column(name="employeename")
	private String empployeeName;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "employeeprofessiondetail")
	private EmployeeProfessionalDetail empDetails;
	

}
