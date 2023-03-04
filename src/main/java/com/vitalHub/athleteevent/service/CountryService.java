package com.vitalHub.athleteevent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitalHub.athleteevent.domain.Country;
import com.vitalHub.athleteevent.resource.CountryRequestResource;
@Service
public interface CountryService {

	public boolean addCountry(CountryRequestResource countryRequestResource);
	
	public List<Country>getAllCountry();
}
