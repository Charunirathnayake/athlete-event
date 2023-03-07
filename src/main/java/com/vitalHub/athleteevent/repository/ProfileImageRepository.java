package com.vitalHub.athleteevent.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitalHub.athleteevent.domain.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long >{
	
	public Optional<ProfileImage> findByAthleteId(Long id);

}
