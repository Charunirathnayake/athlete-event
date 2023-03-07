package com.vitalHub.athleteevent.resource;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class AddAthleteRequestResource {
	
	@NotBlank(message="First name is required.")
	private String firstName;
	
	@NotBlank(message="Last name is required.")
	private String lastName;
	
	@NotBlank(message="Gender is required.")
	private String gender;
	
	@NotBlank(message="Country is required.")
	private String country;
	
	private String image;

	@NotBlank(message="Date of birth is required.")
	private String dateOfBirth;
	
	@NotBlank(message="Created user is required.")
	private String createdUser;
	
	List<EventParticipationResource> eventParticipationList;

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public List<EventParticipationResource> getEventParticipationList() {
		return eventParticipationList;
	}

	public void setEventParticipationList(List<EventParticipationResource> eventParticipationList) {
		this.eventParticipationList = eventParticipationList;
	}
	
}
