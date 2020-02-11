package com.rabi.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabi.swagger.dao.ValidationDao;
import com.rabi.swagger.model.ValidationRequest;
import com.rabi.swagger.model.ValidationResponse;

@Service
public class ValidationService {
	
	@Autowired
	ValidationDao vDao;
	public ValidationResponse processByTranId(final ValidationRequest req) throws Exception {
		return vDao.processByTranId(req);
	}

}
