package com.rabi.swagger.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rabi.swagger.model.ValidationRequest;
import com.rabi.swagger.model.ValidationResponse;
import com.rabi.swagger.util.RabiConstants;

@Repository
public class ValidationDao {
	
	
	public ValidationResponse processByTranId(final ValidationRequest req) throws Exception {
		
		ValidationResponse res= new ValidationResponse();
		switch(req.getwType()) {
		case RabiConstants.CUSTOMER:
			res=executeCustomer(req,res);
			break;
			
		case RabiConstants.ACCOUNT:
			res=executeAccount(req,res);
			break;
			
		default:
			break;
		}
		return res;
	}
	
	
	private ValidationResponse executeCustomer(final ValidationRequest req,final ValidationResponse res) {
		List<Map<String, String>> mapList= new  ArrayList<>();
		Map<String, String> outPutMap =new HashMap<>();
		outPutMap.put("R1", "Customer Rabi");
		outPutMap.put("R2", "Customer Roddu Sankar");
		outPutMap.put("S1", "Customer Swagata");
		
		mapList.add(outPutMap);
		res.setCount(mapList.size());
		res.setMapList(mapList);
		return res;
	}
	
	private ValidationResponse executeAccount(final ValidationRequest req,final ValidationResponse res) {
		List<Map<String, String>> mapList= new  ArrayList<>();
		Map<String, String> outPutMap =new HashMap<>();
		outPutMap.put("R1", "Account Rabi");
		outPutMap.put("R2", "Account Roddu Sankar");
		outPutMap.put("S1", "Account Swagata");
		outPutMap.put("S2", "Account Baba");
		outPutMap.put("S3", "Account Ma");
		
		mapList.add(outPutMap);
		res.setCount(mapList.size());
		res.setMapList(mapList);
		return res;
	}

}
