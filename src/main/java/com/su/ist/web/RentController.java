package com.su.ist.web;


import java.util.List;

import javax.jms.JMSException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.su.ist.domain.Vehicule;
import com.su.ist.service.RentService;

@RestController

public class RentController {

	//list
	@RequestMapping(value = "/voitures", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)  
	@ResponseBody
	public List<Vehicule> listOfCars() throws JMSException{
		RentService rs = new RentService();
		return rs.getUnrentedVehicules();
	}
	
	//test
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)  
	@ResponseBody
	public String test(){
		return "测试";
	}
	
	//Get A car
	@RequestMapping(value = "/voiture/{plateNumber}", method = RequestMethod.GET)  
	@ResponseStatus(HttpStatus.OK)
	public Vehicule aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
		RentService rs = new RentService();
	
		return rs.getCar(plateNumber);
	}
	
	//rent
	 @RequestMapping(value = "/voiture/{plateNumber}", method = RequestMethod.PUT)
	 @ResponseStatus(HttpStatus.OK)
	 public void rent(@PathVariable("plateNumber") String plateNumber) throws Exception{
		 RentService rs = new RentService();
		 rs.rent(plateNumber);
	 } 

	
	//return
	@RequestMapping(value = "/voiture/{plateNumber}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void getBack(@PathVariable("plateNumber") String plateNumber) throws Exception{ 
		RentService rs = new RentService();
		rs.getBack(plateNumber);
		
	}
	
	
	@RequestMapping(value = "/rece", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)  
	@ResponseBody
	public void rece() throws JMSException{
		RentService rs = new RentService();
		rs.recei();
	}
	
	
	
	

	

	/*
	@RequestMapping(value = "/voiture/{plateNumber}", method = RequestMethod.PUT)  
	@ResponseStatus(HttpStatus.OK)
	public void rentAndGetBack(@PathVariable("plateNumber") String plateNumber, @RequestParam(value="rent", required = true)boolean rent) throws Exception{  }*/ 
}
