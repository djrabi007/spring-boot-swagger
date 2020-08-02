package com.rabi.swagger.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

	private static final String AOP_CLASS_NAME = "execution(* com.rabi.swagger.service.EmployeeAOPService.*(..)) ";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceAspect.class);

	@Before(value = AOP_CLASS_NAME + "and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		// System.out.println("Before method:" + joinPoint.getSignature());
		// System.out.println("Creating Employee with name - " + name + " and id - " +
		// empId);
		LOGGER.info("Creating Employee with name - " + name + " and id - " + empId);

	}

	@After(value = AOP_CLASS_NAME + "and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		LOGGER.info("Successfully created Employee with name - " + name + " and id - " + empId);
	}

	@Before(value = AOP_CLASS_NAME + "and args(empId)")
	public void beforeAdviceRemove(JoinPoint joinPoint, String empId) {
		LOGGER.info("Removing Employee with  id - " + empId);
	}
}
