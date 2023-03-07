package com.vitalHub.athleteevent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitalHub.athleteevent.domain.Country;
import com.vitalHub.athleteevent.domain.EventParticipation;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

	Optional<Country>findByCountryName(String name);
}
