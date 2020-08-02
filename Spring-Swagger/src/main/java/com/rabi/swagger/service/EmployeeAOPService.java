package com.rabi.swagger.service;

import org.springframework.stereotype.Service;

import com.rabi.swagger.model.EmployeeAOP;


@Service
public class EmployeeAOPService {

	public EmployeeAOP createEmployee(String name, String empId) {

		EmployeeAOP emp = new EmployeeAOP();
		emp.setName(name);
		emp.setEmpId(empId);
		return emp;
	}

	public void deleteEmployee(String empId) {
		
	}
}