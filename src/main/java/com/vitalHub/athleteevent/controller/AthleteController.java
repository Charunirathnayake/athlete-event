package com.vitalHub.athleteevent.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitalHub.athleteevent.domain.Athlete;
import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.resource.AddAthleteRequestResource;
import com.vitalHub.athleteevent.resource.EventCreationRequestResource;
import com.vitalHub.athleteevent.resource.SuccessAndErrorDetailsResource;
import com.vitalHub.athleteevent.service.AthleteService;

@RestController
@RequestMapping(value = "/athlete")
@CrossOrigin(origins = "*")
public class AthleteController {
	
	@Autowired
	AthleteService athleteService;

	@PostMapping(value = "/creation")
	public ResponseEntity<Object> addAthlete(
			@Valid @RequestBody AddAthleteRequestResource addAthleteRequestResource){
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		Long creationId=athleteService.addAthlete(addAthleteRequestResource);
		if(creationId!=null) {
		successAndErrorDetailsResource.setMessages("Successfully Created.");
		successAndErrorDetailsResource.setValue(creationId.toString());
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.CREATED);}
		else {
			successAndErrorDetailsResource.setMessages("Error Occured");
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping(value = "/creation/{athleteid}")
	public ResponseEntity<Object> addAthleteProfilePicture(@PathVariable(value = "athleteid", required = true)Long athleteid,
			@RequestParam("image") MultipartFile imgFile) throws IOException {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		Boolean value=athleteService.addProfilePicOfAthlete(athleteid, imgFile);
		if(value!=false) {
		successAndErrorDetailsResource.setMessages("Successfully Created.");
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.CREATED);}
		else {
			successAndErrorDetailsResource.setMessages("Error Occured");
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "/all")
    public ResponseEntity<Object> getAllAthletes(){
    	SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
    	List<Athlete>athleteList=athleteService.getAllAthlete();
		
    	if (athleteList!=null && !athleteList.isEmpty()) {
			return new ResponseEntity<>(athleteList,HttpStatus.OK);
		} else {
			responseMessage.setMessages("Records not found.");
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
}
