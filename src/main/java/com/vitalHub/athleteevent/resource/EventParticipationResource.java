package com.vitalHub.athleteevent.resource;

import javax.validation.constraints.NotBlank;

public class EventParticipationResource {
	@NotBlank(message="Event id is required.")
	private String eventId;
	
	@NotBlank(message="Event Name is required.")
	private String eventName;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	
}
