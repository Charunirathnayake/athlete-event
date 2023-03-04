package com.vitalHub.athleteevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitalHub.athleteevent.domain.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete,Long> {
	
}
