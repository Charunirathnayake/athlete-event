package com.vitalHub.athleteevent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.vitalHub.athleteevent.domain.Athlete;
import com.vitalHub.athleteevent.domain.Event;
import com.vitalHub.athleteevent.domain.EventParticipation;
import com.vitalHub.athleteevent.enums.Status;
import com.vitalHub.athleteevent.exception.NoRecordFoundException;
import com.vitalHub.athleteevent.exception.ValidateRecordException;
import com.vitalHub.athleteevent.repository.AthleteRepository;
import com.vitalHub.athleteevent.repository.EventParticipationRepository;
import com.vitalHub.athleteevent.repository.EventRepository;
import com.vitalHub.athleteevent.resource.AddAthleteRequestResource;
import com.vitalHub.athleteevent.resource.EventParticipationResource;
import com.vitalHub.athleteevent.service.AthleteService;
import com.vitalHub.athleteevent.service.ValidateService;

@Component
public class AthleteServiceImpl implements AthleteService  {
	
	@Autowired
	private AthleteRepository athleteRepository;
	
	@Autowired
	private ValidateService validateSevice;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventParticipationRepository eventParticipationRepository;

	@Override
	public Long addAthlete(AddAthleteRequestResource addAthleteRequestResource){
		
		List<EventParticipation> participationList=null;
		
		Athlete athlete=new Athlete();
		athlete.setFirstName(addAthleteRequestResource.getFirstName());
		athlete.setLastName(addAthleteRequestResource.getLastName());
		athlete.setGender(addAthleteRequestResource.getGender());
		athlete.setCountry(addAthleteRequestResource.getCountry());
		athlete.setImage(addAthleteRequestResource.getImage());
		athlete.setDateOfBirth(validateSevice.convertStringToDate(addAthleteRequestResource.getDateOfBirth()));
		athlete.setStatus(Status.ACTIVE.toString());
		athlete.setCreatedUser(addAthleteRequestResource.getCreatedUser());
		
		Athlete savedAthlete=athleteRepository.save(athlete);
		
		
		
		if(!addAthleteRequestResource.getEventParticipationList().isEmpty()) {
			 participationList=saveEventParticipation(addAthleteRequestResource.getEventParticipationList(),savedAthlete,addAthleteRequestResource);
		}	
		return savedAthlete.getId();
	}
	
	private Boolean storeProfileImage(Athlete athlete,MultipartFile imgFile,String fileName) throws IOException {
		String uploadDir="/profile-image/"+athlete.getId();
		Path uploadPath=Paths.get(uploadDir);
		Boolean val=false;
		
		 if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
		 try (InputStream inputStream = imgFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	            val=true;
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }
		return val;   
	}

	private List<EventParticipation> saveEventParticipation(List<EventParticipationResource>eventParticiaption,Athlete athlete,AddAthleteRequestResource addAthleteRequestResource) {
		ArrayList<EventParticipation> eventParticipationList=new ArrayList<>();
		for(EventParticipationResource eventParticipationResourceReq:eventParticiaption) {
			EventParticipation eventParticipationObj=new EventParticipation();
			
			Optional<Event>event=eventRepository.findById(validateSevice.stringToLong(eventParticipationResourceReq.getEventId()));
			if(!event.isPresent()){
				throw new ValidateRecordException("Invalid Event","Event-Id");
			}
			if(!event.get().getName().equalsIgnoreCase(eventParticipationResourceReq.getEventName())) {
				throw new ValidateRecordException("Invalid Event","Event-Name");
			}
			
			eventParticipationObj.setEventId(validateSevice.stringToLong(eventParticipationResourceReq.getEventId()));
			eventParticipationObj.setEventName(eventParticipationResourceReq.getEventName());
			eventParticipationObj.setStatus(Status.ACTIVE.toString());
			eventParticipationObj.setCreatedDate(validateSevice.getCreateOrModifyDate());
			eventParticipationObj.setCreatedUser(addAthleteRequestResource.getCreatedUser());
			eventParticipationObj.setAthleteId(athlete);
			eventParticipationObj.setParticipation("YES");
			
			EventParticipation savEventParticipation=eventParticipationRepository.save(eventParticipationObj);
			eventParticipationList.add(savEventParticipation);
		}
		return eventParticipationList;
	}

	@Override
	public List<Athlete> getAllAthlete() {
		List<Athlete>listOfAthletes= athleteRepository.findAll();
		if(!listOfAthletes.isEmpty()) {
			for(Athlete athleteObj:listOfAthletes) {
				int age=calculateAge(athleteObj.getDateOfBirth());
				athleteObj.setAge(age);
			List<EventParticipation>listOfEventParticipation=eventParticipationRepository.findByAthleteId(athleteObj);
			if(!listOfEventParticipation.isEmpty()) {
				
				athleteObj.setEventParticipationDetails(listOfEventParticipation);
				
				}
			}
		}else {
			throw new NoRecordFoundException("Record not found.");
		}
		return listOfAthletes;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		getUploadedDirectory("profile-image", registry);
		
	}
	
	private int calculateAge(Date dob) {
		Date curDate = new Date();
		int age=curDate.compareTo(curDate);
		return age; 
	}
	private void getUploadedDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }

	@Override
	public Boolean addProfilePicOfAthlete(Long id, MultipartFile imgFile) throws IOException {
		String fileName = StringUtils.cleanPath(imgFile.getOriginalFilename());
		
		Optional<Athlete> athlete=athleteRepository.findById(id);
		athlete.get().setImage(fileName);
		Athlete updatedAthlete=athleteRepository.saveAndFlush(athlete.get());
		
		Boolean val=storeProfileImage(updatedAthlete,imgFile,fileName);
		
		return val;
	}

	@Override
	public List<Athlete> searchAthlete(String searchq, String name, String event, String country, String gender) {
		ArrayList<Athlete> athlete=new ArrayList<>();
		
		
		return athlete;
		
	}
}
