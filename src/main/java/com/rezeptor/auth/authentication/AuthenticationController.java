package com.rezeptor.auth.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rezeptor.utils.UrlMapping;

@RestController
@RequestMapping(UrlMapping.BASE_URL)
public class AuthenticationController {

	private AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping(UrlMapping.SIGN_IN)
	public ResponseEntity<AuthenticationResponse> authenticateAccount(@RequestBody AuthenticationRequest request){
		System.out.println("Sign In Controller");
		AuthenticationResponse authResponse = authService.generateToken(request);
		System.out.println("Sign In Controller For Generate");
		return (ResponseEntity<AuthenticationResponse>) ResponseEntity
														.ok()
														.header(HttpHeaders.AUTHORIZATION, authResponse.token())
														.body(authResponse);
	}
	
}
