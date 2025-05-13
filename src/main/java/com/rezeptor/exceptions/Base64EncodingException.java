package com.rezeptor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class Base64EncodingException extends RuntimeException{

	public Base64EncodingException(String message) {
		super(message);
	}
	
	public Base64EncodingException(String message,MaxDataSizeException e) {
		super(message,e);
	}
	
	public Base64EncodingException(String message,Exception e) {
		super(message,e);
	}
}
