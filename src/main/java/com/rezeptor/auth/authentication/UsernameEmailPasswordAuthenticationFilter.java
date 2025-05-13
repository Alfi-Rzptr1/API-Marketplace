package com.rezeptor.auth.authentication;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rezeptor.exceptions.AuthenticationServiceException;
import com.rezeptor.utils.UrlMapping;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UsernameEmailPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationController authController;
	
	public UsernameEmailPasswordAuthenticationFilter(AuthenticationManager authenticationManager,AuthenticationController authenticationController) {
		super(authenticationManager);
		setFilterProcessesUrl(UrlMapping.SIGN_IN);
		this.authController = authenticationController;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("Attempt Authentication");
		AuthenticationRequest authRequest = extractAuthRequest(request);
		
		String username = authRequest.username();
		String email = authRequest.email();
		String password = authRequest.password();
		
		if((username == null || username.isEmpty()) && (email == null || email.isEmpty())) {
			throw new AuthenticationServiceException("Authentication failed : need username or email");
		}
		if(password == null || password.isEmpty()) {
			throw new AuthenticationServiceException("Authentication failed : need password");
		}
		
		boolean withUsername = (username != null && !username.isEmpty());
		String principal = withUsername ? username : email;
		
		UsernameEmailPasswordAuthenticationToken authToken = new UsernameEmailPasswordAuthenticationToken(principal,password,withUsername);
		return this.getAuthenticationManager().authenticate(authToken);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
				System.out.println("Success");
				SecurityContextHolder.getContext().setAuthentication(authResult);
		
				ResponseEntity<?> responseEntity = authController.authenticateAccount(extractAuthRequest(request));
		
				response.setStatus(responseEntity.getStatusCode().value());
				response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
				ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
																	  .body("Error: " + failed.getMessage());
				
				response.setStatus(responseEntity.getStatusCode().value());
				response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
	}

	private AuthenticationRequest extractAuthRequest(HttpServletRequest request) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(request.getInputStream(),AuthenticationRequest.class);
		} catch (Exception e) {
			throw new AuthenticationServiceException("Authentication failed : unable to read the request");
		}
	}
	
}
