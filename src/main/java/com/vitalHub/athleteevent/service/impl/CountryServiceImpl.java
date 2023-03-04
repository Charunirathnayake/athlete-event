package com.vitalHub.athleteevent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitalHub.athleteevent.domain.Country;
import com.vitalHub.athleteevent.exception.DuplicateRecordException;
import com.vitalHub.athleteevent.repository.CountryRepository;
import com.vitalHub.athleteevent.resource.CountryRequestResource;
import com.vitalHub.athleteevent.service.CountryService;
import com.vitalHub.athleteevent.service.ValidateService;

@Component
public class CountryServiceImpl implements CountryService {

	@Autowired
	private ValidateService validateService;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public boolean addCountry(CountryRequestResource countryRequestResource) {
		boolean val=false;
		
		Country country=new Country();
		Optional<Country> existCountry=countryRepository.findByCountryName(countryRequestResource.getCountryName());
		if(existCountry.isPresent()) {
			throw new DuplicateRecordException("Country cannot be Duplicate","country");
		}else {
		country.setCountryName(countryRequestResource.getCountryName());
		country.setCreatedUser(countryRequestResource.getUserName());
		country.setCreatedDate(validateService.getCreateOrModifyDate());
		countryRepository.save(country);
		val=true;
		return val;
		}
	}

	@Override
	public List<Country> getAllCountry() {
	List<Country> listOfCountries=countryRepository.findAll();
		return listOfCountries;
	}

}
