package com.luv2code.springboot.BlogApp.CustomException;

import org.springframework.http.HttpStatus;

public class BlogApiExceptions extends RuntimeException {
	
	private HttpStatus status;
	private String message;
	public BlogApiExceptions(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
		
	}
	
	public BlogApiExceptions() {
		
	}

	
	public String getMessage() {
		return message;
	}

	

	public HttpStatus getStatus() {
		return status;
	}

	
	
	
	
	
	

}
