package com.vitalHub.athleteevent.service.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.vitalHub.athleteevent.domain.ProfileImage;
import com.vitalHub.athleteevent.repository.ProfileImageRepository;
import com.vitalHub.athleteevent.service.ProfileImageService;

@Component
public class ProfileImageServiceImpl implements ProfileImageService{

	@Autowired
	private ProfileImageRepository profileImageRepository;
	
	@Override
	public boolean uploadImage(MultipartFile file,Long athleteId) throws IOException {
		
		ProfileImage profileImage=new ProfileImage();
		String imgName=file.getOriginalFilename();
		String type=file.getContentType();
		profileImage.setAthleteId(athleteId);	
		profileImage.setName(imgName);
		profileImage.setPicByte(compressBytes(file.getBytes()));
		profileImage.setType(type);
		profileImageRepository.save(profileImage);
		return true;
	}
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		
		        byte[] buffer = new byte[1024];
		        while (!deflater.finished()) {
	            int count = deflater.deflate(buffer);
		        outputStream.write(buffer, 0, count);
		      }
		        try {
		            outputStream.close();
		        } catch (IOException e) {
		
		        }
		
		        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		        return outputStream.toByteArray();		
		    }

	@Override
	public ProfileImage getImage(Long athleteId) {
		Optional<ProfileImage> retrievedImage = profileImageRepository.findByAthleteId(athleteId);
		ProfileImage profileImage=new ProfileImage();
		profileImage.setAthleteId(retrievedImage.get().getAthleteId());
		profileImage.setId(retrievedImage.get().getId());
		profileImage.setName(retrievedImage.get().getName());
		profileImage.setPicByte(decompressBytes(retrievedImage.get().getPicByte()));	
		return profileImage;
	}
	 public static byte[] decompressBytes(byte[] data) {
		 
		         Inflater inflater = new Inflater(); 
		         inflater.setInput(data);
		 
		         ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		         byte[] buffer = new byte[1024];
		 
		         try {
		             while (!inflater.finished()) {
		                 int count = inflater.inflate(buffer);
		                 outputStream.write(buffer, 0, count);
		             }
		             outputStream.close();
		         } catch (IOException ioe) {
		 
		         } catch (DataFormatException e) {
		         }
		         return outputStream.toByteArray();
		     }
}
