package com.vitalHub.athleteevent.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.resource.EventCreationRequestResource;
import com.vitalHub.athleteevent.resource.SuccessAndErrorDetailsResource;
import com.vitalHub.athleteevent.service.EventService;


@RestController
@RequestMapping(value = "/event")
@CrossOrigin(origins = "*")
public class EventController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private EventService eventService;
	
	@PostMapping(value = "/creation")
	public ResponseEntity<Object> createEvent(
			@Valid @RequestBody EventCreationRequestResource eventCreationRequestResource) {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		Long creationId=eventService.createEvent(eventCreationRequestResource);
		if(creationId!=null) {
		successAndErrorDetailsResource.setMessages("Successfully Created.");
		successAndErrorDetailsResource.setValue(creationId.toString());
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.CREATED);}
		else {
			successAndErrorDetailsResource.setMessages("Error Occured");
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	 @GetMapping(value = "/all")
	    public ResponseEntity<Object> getAllEvents(){
	    	SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
	    	List<Event> event=eventService.getAllEvents();
			
	    	if (event!=null && !event.isEmpty()) {
				return new ResponseEntity<>(event,HttpStatus.OK);
			} else {
				responseMessage.setMessages("Records not found.");
				return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
			}
		}

}
