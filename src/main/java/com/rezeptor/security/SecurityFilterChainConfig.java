package com.rezeptor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rezeptor.auth.authentication.UsernameEmailPasswordAuthenticationFilter;
import com.rezeptor.utils.UrlMapping;
import com.rezeptor.utils.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

	private JWTAuthenticationFilter jwtAuthenticationFilter;
	private AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityFilterChainConfig(JWTAuthenticationFilter jwtAuthenticationFilter,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
    										AuthenticationManager authManager,
    										UsernameEmailPasswordAuthenticationFilter usernameEmailPasswordAuthenticationFilter) throws Exception {
		httpSecurity
			.csrf((csrf) -> csrf.disable())
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.POST,
					                                                       UrlMapping.BASE_URL + UrlMapping.SIGN_IN,
					                                                       UrlMapping.BASE_URL + UrlMapping.SIGN_UP).permitAll()
					                                       .requestMatchers(HttpMethod.GET,
							                                                "/ping",
							                                                "/api/v1/*/account-image").permitAll()
					                                       .anyRequest().authenticated())
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationManager(authManager)
			.addFilterAt(usernameEmailPasswordAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling((exception) -> exception.authenticationEntryPoint(authenticationEntryPoint));
		return httpSecurity.build();
		
	}
}
