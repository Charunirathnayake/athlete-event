package com.vitalHub.athleteevent.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.vitalHub.athleteevent.service.ValidateService;
@Component
public class ValidateServiceImpl implements ValidateService{
	@Override
	public Timestamp getCreateOrModifyDate() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		return new Timestamp(now.getTime());
	}
	
	@Override
	public BigDecimal doubleToBigDecimal(double value) {
		return BigDecimal.valueOf(value);
	}

	@Override
	public Date convertStringToDate(String date) {
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
	Date formattedDate = null;
	try {
		formattedDate = formatter.parse(date);
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return formattedDate;
	}
	
	@Override
	public Long stringToLong(String str) {
		return Long.parseLong(str);
	}
}
