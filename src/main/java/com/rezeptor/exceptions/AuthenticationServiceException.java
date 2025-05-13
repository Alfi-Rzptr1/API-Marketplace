package com.rezeptor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationServiceException extends RuntimeException{

	public AuthenticationServiceException(String message) {
		super(message);
	}
}

