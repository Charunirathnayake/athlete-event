package com.vitalHub.athleteevent.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitalHub.athleteevent.domain.Country;
import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.resource.AddAthleteRequestResource;
import com.vitalHub.athleteevent.resource.CountryRequestResource;
import com.vitalHub.athleteevent.resource.SuccessAndErrorDetailsResource;
import com.vitalHub.athleteevent.service.CountryService;

@RestController
@RequestMapping(value = "/country")
@CrossOrigin(origins = "*")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@PostMapping(value = "/creation")
	public ResponseEntity<Object> createAthlete(
			@Valid @RequestBody CountryRequestResource countryRequestResource){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		Boolean val=countryService.addCountry(countryRequestResource);
		
		if(val!=false) {
		successAndErrorDetailsResource.setMessages("Successfully Created.");
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.CREATED);}
		else {
			successAndErrorDetailsResource.setMessages("Error Occured");
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "/all")
    public ResponseEntity<Object> getAllCountries(){
    	SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
    	List<Country> listOfCountry=countryService.getAllCountry();
		
    	if (listOfCountry!=null && !listOfCountry.isEmpty()) {
			return new ResponseEntity<>(listOfCountry,HttpStatus.OK);
		} else {
			responseMessage.setMessages("Records not found.");
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
}
