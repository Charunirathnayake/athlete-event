package com.vitalHub.athleteevent.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "event_participation")
public class EventParticipation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="event_id")
	private Long eventId;
	
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="participation")
	private String participation;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
	private Athlete athleteId;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="modified_user")
	private String modifiedUser;
	
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	
	@Column(name="result")
	private String result;

	
	public String getParticipation() {
		return participation;
	}

	public void setParticipation(String participation) {
		this.participation = participation;
	}

	public Athlete getAthleteId() {
		return athleteId;
	}

	public void setAthleteId(Athlete athleteId) {
		this.athleteId = athleteId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

}
