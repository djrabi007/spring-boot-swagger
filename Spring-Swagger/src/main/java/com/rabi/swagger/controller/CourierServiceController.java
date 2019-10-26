package com.rabi.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.model.CourierRequest;
import com.rabi.swagger.model.CourierResponse;
import com.rabi.swagger.service.CourierService;

@RestController
public class CourierServiceController {
	
	@Autowired
	CourierService serv;
	@PostMapping("/processCourier")
	public CourierResponse dispacthCourier
			(@RequestBody CourierRequest req) {
		return serv.processCourier(req);
	}

}
