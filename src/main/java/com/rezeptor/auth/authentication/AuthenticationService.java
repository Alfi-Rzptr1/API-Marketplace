package com.rezeptor.auth.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rezeptor.modules.account.Account;
import com.rezeptor.modules.account.AccountDTO;
import com.rezeptor.modules.account.AccountDTOMapper;
import com.rezeptor.utils.jwt.JWTUtil;

@Service
public class AuthenticationService {

	private AccountDTOMapper accountDTOMapper;
	private JWTUtil jwtUtil;
	
	public AuthenticationService(AccountDTOMapper accountDTOMapper, JWTUtil jwtUtil) {
		this.accountDTOMapper = accountDTOMapper;
		this.jwtUtil = jwtUtil;
	}

	public AuthenticationResponse generateToken(AuthenticationRequest request) {
		System.out.println("Generate Token Service");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account principal = (Account) auth.getPrincipal();
		AccountDTO accountDTO = accountDTOMapper.apply(principal);
		String token = jwtUtil.issueJWTToken(accountDTO.username(),accountDTO.roles());
		return new AuthenticationResponse(token, accountDTO);
	}
	
}
