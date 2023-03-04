package com.vitalHub.athleteevent.resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
public class EventCreationRequestResource {

	@NotBlank(message="Event name is required.")
	private String eventName;
	
	@NotBlank(message="UserName is required.")
	private String createdUser;

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
