package com.vitalHub.athleteevent.core;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.vitalHub.athleteevent.exception.DuplicateRecordException;
import com.vitalHub.athleteevent.exception.NoRecordFoundException;
import com.vitalHub.athleteevent.exception.UserNotFound;
import com.vitalHub.athleteevent.exception.ValidateRecordException;
import com.vitalHub.athleteevent.resource.SuccessAndErrorDetailsResource;
import com.vitalHub.athleteevent.resource.ValidationCommon;


@RestControllerAdvice
public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@InitBinder
    public void initBinder(DataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	private String commonInternalServerError="common.internal-server-error";
	private String userNotFound="common.user-not-found";
	private String project="athlete-event";
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler({DuplicateRecordException.class})
	public ResponseEntity<Object> duplicateRecordException(DuplicateRecordException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(ex.getMessage());
		successAndErrorDetailsDataObject.setCode(ErrorCode.COMN00003);
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({UserNotFound.class})
	public ResponseEntity<Object> userNotFoundException(UserNotFound ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsResource.setMessages(environment.getProperty(userNotFound));
		successAndErrorDetailsResource.setCode(ErrorCode.COMN00007);
		LoggerRequest.getInstance().logCommonError(project+ " " +successAndErrorDetailsResource.getCode()+" "+successAndErrorDetailsResource.getMessages()+" "+successAndErrorDetailsResource.getDetails());
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NO_CONTENT);
	}
	
	 @ExceptionHandler({ValidateRecordException.class})
	    public ResponseEntity<Object> validateRecordException(ValidateRecordException ex, WebRequest request) {
	        try {
	        	 String fieldName=null;
				 Integer index=null;
				 Field sField=null;
				 Integer atPoint=null;
		        ValidationCommon typeValidation = new ValidationCommon();
		        Class validationDetailClass = typeValidation.getClass();
		        LoggerRequest.getInstance().logInfo(project+ " " +ex.getField()+" "+ex.getMessage());
		        fieldName=ex.getField();
		         
		        	 sField=typeValidation.getClass().getDeclaredField(fieldName);
		        	 sField.setAccessible(true);
					 sField.set(typeValidation, ex.getMessage());
		         

		        return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
	        }
	        catch (Exception e) {

	            SuccessAndErrorDetailsResource successAndErrorDetailsDto = new SuccessAndErrorDetailsResource();
	            successAndErrorDetailsDto.setMessages(environment.getProperty("common.internal-server-error"));
	            successAndErrorDetailsDto.setDetails(e.getMessage());
	            successAndErrorDetailsDto.setCode(ErrorCode.COMN00003);
	            LoggerRequest.getInstance().logCommonError(project+ " " +successAndErrorDetailsDto.getCode()+" "+successAndErrorDetailsDto.getMessages()+" "+successAndErrorDetailsDto.getDetails());
	            return new ResponseEntity<>(successAndErrorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
}
