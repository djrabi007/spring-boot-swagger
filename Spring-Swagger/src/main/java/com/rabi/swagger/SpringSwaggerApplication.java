package com.rabi.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true) // AOP implemetation
public class SpringSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSwaggerApplication.class, args);
	}

}
