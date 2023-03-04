package com.vitalHub.athleteevent.resource;

import javax.validation.constraints.NotBlank;

public class EventParticipationResource {
	@NotBlank(message="Event id is required.")
	private String eventId;
	
	@NotBlank(message="Event Name is required.")
	private String eventName;
	
	@NotBlank(message="Created user is required.")
	private String createdUser;

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

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	
}
