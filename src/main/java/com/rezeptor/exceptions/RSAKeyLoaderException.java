package com.rezeptor.exceptions;

@SuppressWarnings("serial")
public class RSAKeyLoaderException extends RuntimeException{

	public RSAKeyLoaderException(String message) {
		super(message); 
	}
	
	public RSAKeyLoaderException(String message,Throwable cause) {
		super(message,cause);
	}
	
}
