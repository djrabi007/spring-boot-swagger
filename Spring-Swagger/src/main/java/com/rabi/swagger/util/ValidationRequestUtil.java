package com.rabi.swagger.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.rabi.swagger.exception.ValidationException;
import com.rabi.swagger.model.ValidationRequest;
import com.rabi.swagger.model.ValidationResponse;


public class ValidationRequestUtil {
	
	private List<String> errorList =new ArrayList<>();

	public  ValidationRequest populateRequest(String tranId,String wType,String businessDate) {

		final ValidationRequest req= new ValidationRequest();
		req.setTransactionId(Long.valueOf(Optional.ofNullable(tranId).orElseGet(()->"-1")));
		req.setwType(validateWType(wType));
		req.setBusinessDate(validateBusinessDate(businessDate));
		
		return req;
	}
	
	public ValidationResponse populateErrorResponse(final List<String> errorValList) {
		final ValidationResponse resp= new ValidationResponse();
		resp.setErrorList(errorValList);
		return resp;
	}
	
	
	
	private String validateWType(String wType) {
		if(!StringUtils.isEmpty(wType)) {
			wType= wType.toLowerCase();
		}
		else {
			errorList.add(RabiConstants.W_TYPE_NOT_VALID);
		}
		
		switch(wType) {
			case RabiConstants.CUSTOMER:
						break;
			case RabiConstants.ACCOUNT:
				break;
			default:
				errorList.add(RabiConstants.W_TYPE_NOT_VALID);
				break;
		}
		
		return wType;
	}
	
	
	public String validateBusinessDate(String businessDate) {
		if(!StringUtils.isEmpty(businessDate)) {
			try {
				businessDate = RabiUtil.convertJavaToSQLDate(businessDate);
			}
			catch(ParseException|ValidationException e) {
				errorList.add(RabiConstants.DATE_FORMAT_NOT_CORRECT);
				
			}
		}
		return businessDate;
	}
	
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

}
