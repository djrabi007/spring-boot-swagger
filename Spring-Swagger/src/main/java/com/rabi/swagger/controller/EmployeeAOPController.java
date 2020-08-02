package com.rabi.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.model.EmployeeAOP;
import com.rabi.swagger.service.EmployeeAOPService;


@RestController
@RequestMapping("/employeeAOPService")
public class EmployeeAOPController {

	@Autowired
	private EmployeeAOPService employeeService;

	@RequestMapping(value = "/add/employee", method = RequestMethod.GET)
	public EmployeeAOP addEmployee(@RequestParam("name") String name, @RequestParam("empId") String empId) {

		return employeeService.createEmployee(name, empId);

	}

	@RequestMapping(value = "/remove/employee", method = RequestMethod.GET)
	public String removeEmployee( @RequestParam("empId") String empId) {

		employeeService.deleteEmployee(empId);

		return "Employee removed";
	}

}