package com.vitalHub.athleteevent.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitalHub.athleteevent.domain.ProfileImage;
import com.vitalHub.athleteevent.resource.SuccessAndErrorDetailsResource;
import com.vitalHub.athleteevent.service.ProfileImageService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

@RestController
@RequestMapping(value = "/profile-image")
@CrossOrigin(origins = "*")
public class ProfileImgController {
	@Autowired
	private ProfileImageService profileImageService;
	
	@PostMapping("/upload/{athleteid}")
	 public BodyBuilder uplaodImage(@PathVariable(value = "athleteid", required = true)Long athleteid,@RequestParam("imageFile") MultipartFile file)throws IOException  {
		profileImageService.uploadImage(file,athleteid);
		return ResponseEntity.status(HttpStatus.OK);
		
	}
	
	@GetMapping(path = { "/get/{athleteId}" })
	public ResponseEntity<Object> getImage(@PathVariable("athleteId") Long athleteId) throws IOException {
		ProfileImage profileImage =profileImageService.getImage(athleteId);
		SuccessAndErrorDetailsResource successAndErrorDetailsResource=new SuccessAndErrorDetailsResource();
		if(profileImage!=null) {
			successAndErrorDetailsResource.setMessages("Successfully Created.");
			return new ResponseEntity<>(profileImage, HttpStatus.CREATED);
		}else {
			successAndErrorDetailsResource.setMessages("Error Occured");
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
}
