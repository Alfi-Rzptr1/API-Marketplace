package com.rezeptor.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

		@ExceptionHandler({ResourceNotFoundException.class,EntityNotFoundException.class})
		public ResponseEntity<ApiError> handleException(ResourceNotFoundException e,
														HttpServletRequest request) {
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.NOT_FOUND.value(),
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(InsufficientAuthenticationException.class)
		public ResponseEntity<ApiError> handleException(InsufficientAuthenticationException e,
														HttpServletRequest request){
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.FORBIDDEN.value(),
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError,HttpStatus.FORBIDDEN);
		}
		
		@ExceptionHandler({BadCredentialsException.class,AuthenticationServiceException.class})
		public ResponseEntity<ApiError> handleException(BadCredentialsException e,
														HttpServletRequest request){
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.UNAUTHORIZED.value(),
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError,HttpStatus.UNAUTHORIZED);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ApiError> handleException(Exception e,
														HttpServletRequest request){
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.INTERNAL_SERVER_ERROR.value(),
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(ResourceConflictException.class)
		public ResponseEntity<ApiError> handleException(ResourceConflictException e,
														HttpServletRequest request){
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.CONFLICT.value(), 
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError,HttpStatus.CONFLICT);
		}
		
		@ExceptionHandler(ResourceGoneException.class)
		public ResponseEntity<ApiError> handleException(ResourceGoneException e,
														HttpServletRequest request){
			ApiError apiError = new ApiError(request.getRequestURI(),
											 e.getMessage(),
											 HttpStatus.GONE.value(), 
											 LocalDateTime.now());
			return new ResponseEntity<ApiError>(apiError,HttpStatus.GONE);
		}
}
