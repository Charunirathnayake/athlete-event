package com.vitalHub.athleteevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitalHub.athleteevent.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long >{

}
