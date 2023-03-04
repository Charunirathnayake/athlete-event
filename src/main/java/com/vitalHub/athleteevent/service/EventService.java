package com.vitalHub.athleteevent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.resource.EventCreationRequestResource;
@Service
public interface EventService {

	/**
	 * @param eventCreationRequestResource
	 * @return
	 */
	public Long createEvent(EventCreationRequestResource eventCreationRequestResource);
	/**
	 * @return
	 */
	public List<Event> getAllEvents();
}
