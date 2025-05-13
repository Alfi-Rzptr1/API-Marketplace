package com.rezeptor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rezeptor.auth.authentication.UsernameEmailPasswordAuthenticationProvider;
import com.rezeptor.modules.account.AccountLogicService;

@Configuration
public class AuthenticationManagerConfig {
	
	private UsernameEmailPasswordAuthenticationProvider usernameEmailPasswordAuthenticationProvider;
	private DaoAuthenticationProvider daoAuthenticationProvider;

	public AuthenticationManagerConfig(
			DaoAuthenticationProvider daoAuthenticationProvider, UsernameEmailPasswordAuthenticationProvider usernameEmailPasswordAuthenticationProvider) {
		this.usernameEmailPasswordAuthenticationProvider = usernameEmailPasswordAuthenticationProvider;
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
													   AccountLogicService accountService,
													   PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = 
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(usernameEmailPasswordAuthenticationProvider);
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);
		return authenticationManagerBuilder.build();
	}
	
}
