package com.rezeptor.auth.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rezeptor.utils.UrlMapping;

@RestController
@RequestMapping(UrlMapping.BASE_URL)
public class RegistrationController {

	private RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping(UrlMapping.SIGN_UP)
	public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request){
		String link = registrationService.register(request);
		RegistrationResponse response = new RegistrationResponse(link);
		return new ResponseEntity<RegistrationResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(UrlMapping.SIGN_UP + "/confirm")
	public ResponseEntity<?> confirmToken(@RequestParam String token){
		String res = registrationService.confirmToken(token);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	
}
