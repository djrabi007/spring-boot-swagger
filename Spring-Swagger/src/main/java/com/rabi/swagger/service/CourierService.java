package com.rabi.swagger.service;

import java.util.Random;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Service;

import com.rabi.swagger.model.CourierRequest;
import com.rabi.swagger.model.CourierResponse;

import io.sentry.Sentry;

@Service
public class CourierService {

	private static final  
	 	String	SENTRY_DSN = "https://295e676ee433496b930a2b9672ff9fa8@sentry.io/1795693";
	private static final 
		String SERVICE_NOT_AVAILABLE_ = "Service not available for your  location ";
	
	public CourierResponse 
			processCourier(CourierRequest req) {
		CourierResponse resp =null;
		Sentry.init(SENTRY_DSN);
		
		try {
		if(!isServiceAvailable(req.getPin())) {
		throw new ServiceNotFoundException(SERVICE_NOT_AVAILABLE_+req.getPin());
		}
		}catch(Exception  ex) {
			Sentry.capture(ex);
			return resp= new CourierResponse("**","FAIL","Could not Process");
		}
		return resp= new CourierResponse("**","SUCCESS","10/26/2019");
	}
	
	public boolean isServiceAvailable(String pin) {
		return new Random().nextBoolean();
	}
}
