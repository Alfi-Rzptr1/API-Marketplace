package com.rezeptor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.GONE)
public class ResourceGoneException extends RuntimeException{

	public ResourceGoneException(String message) {
		super(message);
	}
	
}
