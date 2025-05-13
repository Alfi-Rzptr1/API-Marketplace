package com.rezeptor.auth.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rezeptor.exceptions.AuthenticationServiceException;
import com.rezeptor.modules.account.Account;
import com.rezeptor.modules.account.AccountLogicService;

@Component
public class UsernameEmailPasswordAuthenticationProvider implements AuthenticationProvider{

	private AccountLogicService accountService;
	private PasswordEncoder passwordEncoder;
	
	public UsernameEmailPasswordAuthenticationProvider(AccountLogicService accountService, PasswordEncoder passwordEncoder) {
		this.accountService = accountService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernameEmailPasswordAuthenticationToken authToken = (UsernameEmailPasswordAuthenticationToken) authentication;
		String principal = (String) authToken.getPrincipal();
		String credential = (String) authToken.getCredentials();
		boolean withUsername = authToken.getWithUsername();
		
		Account account;
		
		if(withUsername) {
			account = accountService.findByUsername(credential).orElseThrow(
					() -> new AuthenticationServiceException("Invalid username/email or password"));
		}else {
			account = accountService.findByEmail(principal).orElseThrow(
					() -> new AuthenticationServiceException("Invalid username/email or password"));
		}
		
		if(!passwordEncoder.matches(credential, account.getPassword())) {
			throw new AuthenticationServiceException("Invalid username/email or password");
		}
		
		return new UsernameEmailPasswordAuthenticationToken(account.getId(), account.getUsername(), account.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernameEmailPasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
