package com.vitalHub.athleteevent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.enums.Status;
import com.vitalHub.athleteevent.exception.NoRecordFoundException;
import com.vitalHub.athleteevent.repository.EventRepository;
import com.vitalHub.athleteevent.resource.EventCreationRequestResource;
import com.vitalHub.athleteevent.service.EventService;
import com.vitalHub.athleteevent.service.ValidateService;

@Component
public class EventServiceImpl implements EventService{

	@Autowired
	ValidateService validateService;
	
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public Long createEvent(EventCreationRequestResource eventCreationRequestResource) {
	
		Event event=new Event();
		event.setName(eventCreationRequestResource.getEventName());
		event.setCreatedUser(eventCreationRequestResource.getCreatedUser());
		event.setCreatedDate(validateService.getCreateOrModifyDate());
		event.setStatus(Status.ACTIVE.toString());
		
		Event savedEvent=eventRepository.save(event);
		return savedEvent.getId();		
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> event=eventRepository.findAll();
		if(!event.isEmpty()) {
			return event;
		}else {
			throw new NoRecordFoundException("Record not found.");
		}
		
	}

}
