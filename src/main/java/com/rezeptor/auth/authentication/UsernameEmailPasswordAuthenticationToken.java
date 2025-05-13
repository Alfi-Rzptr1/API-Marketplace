package com.rezeptor.auth.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class UsernameEmailPasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{

	private final Long id;
	private final Boolean withUsername;
	
	public UsernameEmailPasswordAuthenticationToken(Object principal, Object credentials, Boolean withUsername) {
		super(principal, credentials);
		this.id = null;
		this.withUsername = withUsername;
	}
	
	public UsernameEmailPasswordAuthenticationToken(Long id, Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(principal, null,authorities);
		this.id = id;
		this.withUsername = null;
	}
	
	public boolean isWithUsername() {
		return withUsername;
	}

	public Long getId() {
		return id;
	}

	public Boolean getWithUsername() {
		return withUsername;
	}
	
}
