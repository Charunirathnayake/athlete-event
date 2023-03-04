package com.vitalHub.athleteevent.exception;

public class DuplicateRecordException extends RuntimeException {

	private final String field;
	
	public DuplicateRecordException (String exception,String field) {
		super(exception);
		this.field = field;
	}
   
	public String getField() {return this.field;}
}
