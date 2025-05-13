package com.rezeptor.auth.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

	private ConfirmationTokenRepo confirmationTokenRepo;
	
	public ConfirmationTokenService(ConfirmationTokenRepo confirmationTokenRepo) {
		this.confirmationTokenRepo = confirmationTokenRepo;
	}

	public Optional<ConfirmationToken> getToken(String token){
		return confirmationTokenRepo.findByToken(token);
	}
	
	public void createToken(ConfirmationToken token) {
		confirmationTokenRepo.save(token);
	}
	
	public int setConfirmedAt(String token) {
		return confirmationTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
	}
	
}
