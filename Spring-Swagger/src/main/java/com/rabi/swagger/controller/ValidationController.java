package com.rabi.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.model.ValidationRequest;
import com.rabi.swagger.model.ValidationResponse;
import com.rabi.swagger.service.BookService;
import com.rabi.swagger.service.ValidationService;
import com.rabi.swagger.util.ValidationRequestUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/validationRabi")
public class ValidationController {
	@Autowired
	ValidationService vService;
	
	
	
	@GetMapping("/service/XXXDecode")
	@ApiOperation(value="Validation and Exception Handling")
	public ValidationResponse searchByTransactionId(
			@RequestParam(value="tranId", required= true)String tranId,
			@RequestParam(value="wType", required= true)String wType,
			@RequestParam(value="businessDate", required= false)String businessDate
			)  throws Exception {
		final ValidationRequestUtil vUtil= new ValidationRequestUtil();
		final ValidationRequest req = vUtil.populateRequest(tranId, wType, businessDate);
		if(!vUtil.getErrorList().isEmpty()) {
			return vUtil.populateErrorResponse(vUtil.getErrorList());
		}
		else {
			return vService.processByTranId(req);
		}
		
	}
	
		
}
