package com.vitalHub.athleteevent.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.vitalHub.athleteevent.domain.Athlete;
import com.vitalHub.athleteevent.resource.AddAthleteRequestResource;

@Service
public interface AthleteService {
	
	public Long addAthlete(AddAthleteRequestResource addAthleteRequestResource);
	
	public List<Athlete>getAllAthlete();
	
	public void addResourceHandlers(ResourceHandlerRegistry registry);
	
	public Boolean addProfilePicOfAthlete(Long id,MultipartFile imgFile) throws IOException;
	
	public List<Athlete> searchAthlete(String searchq,String name,String event,String country,String gender);

}
