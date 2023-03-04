package com.vitalHub.athleteevent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitalHub.athleteevent.domain.EventParticipation;

@Repository
public interface EventParticipationRepository extends JpaRepository<EventParticipation,Long>{
	
	List<EventParticipation>findByAthleteId(Long id);

}
