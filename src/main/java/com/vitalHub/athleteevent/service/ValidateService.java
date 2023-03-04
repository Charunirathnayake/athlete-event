package com.vitalHub.athleteevent.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public interface ValidateService {

	/**
	 * @return
	 */
	public Timestamp getCreateOrModifyDate();
	
	/**
	 * @param value
	 * @return
	 */
	public BigDecimal doubleToBigDecimal(double value);
	
	/**
	 * @param date
	 * @return
	 */
	public Date convertStringToDate(String date);
	
	/**
	 * @param str
	 * @return
	 */
	public Long stringToLong(String str);
}
