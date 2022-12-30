package com.luv2code.springboot.BlogApp.CustomException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luv2code.springboot.BlogApp.Dto.ErrorDetails;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleexception(ResourceNotFoundException exception , WebRequest request){
		
		
		ErrorDetails errordetails = new ErrorDetails(new Date() , exception.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(errordetails , HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handelexception(Exception exception , WebRequest request){
		
		ErrorDetails errordetails = new ErrorDetails(new Date() , exception.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(errordetails , HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String , String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String Field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(Field, message);
		});
		return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
		
}
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
