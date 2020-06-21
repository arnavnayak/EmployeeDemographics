package com.employeedomain.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="employeeprofessionaldetails")
@Data
public class EmployeeProfessionalDetail implements Serializable{

	
public EmployeeProfessionalDetail() {}

	public EmployeeProfessionalDetail(int id,String empRole, float exp, String projectName) {
		this.id = id;
		this.empRole = empRole;
		this.exp = exp;
		this.projectName = projectName;
	}
	private static final long serialVersionUID = -7731498637669754269L;
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int id;
	@Column(name="role")
	private String empRole;
	@Column(name="exp")
	private float exp;
	@Column(name="projectname")
	private String projectName;

}
