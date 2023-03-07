package com.vitalHub.athleteevent.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vitalHub.athleteevent.domain.ProfileImage;

@Service
public interface ProfileImageService {

	public boolean uploadImage(MultipartFile file,Long athleteId)throws IOException ;
	
	public ProfileImage getImage(Long athleteId);
}
