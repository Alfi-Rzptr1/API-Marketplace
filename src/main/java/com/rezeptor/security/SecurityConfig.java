package com.rezeptor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rezeptor.auth.authentication.AuthenticationController;
import com.rezeptor.auth.authentication.UsernameEmailPasswordAuthenticationFilter;
import com.rezeptor.auth.authentication.UsernameEmailPasswordAuthenticationProvider;
import com.rezeptor.modules.account.AccountLogicService;

@Configuration
public class SecurityConfig{
	

	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    UsernameEmailPasswordAuthenticationFilter usernameEmailPasswordAuthenticationFilter(AuthenticationManager authManager,AuthenticationController authController) {
    	//this.usernameEmailPasswordAuthenticationFilter = usernameEmailPasswordAuthenticationFilter(authManager);
    	//return usernameEmailPasswordAuthenticationFilter(authManager);
    	return new UsernameEmailPasswordAuthenticationFilter(authManager, authController);
    }
    
//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity http,
//    											AccountService accountService,
//    											PasswordEncoder passwordEncoder) throws Exception{
//		AuthenticationManagerBuilder authenticationManagerBuilder = 
//				http.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.authenticationProvider(usernameEmailPasswordAuthenticationProvider(accountService,passwordEncoder));
//		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider(accountService,passwordEncoder));
//		return authenticationManagerBuilder.build();
//	}

    @Bean
    UsernameEmailPasswordAuthenticationProvider usernameEmailPasswordAuthenticationProvider(AccountLogicService accountService,PasswordEncoder passwordEncoder) {
		return new UsernameEmailPasswordAuthenticationProvider(accountService,passwordEncoder);
	}

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(AccountLogicService accountService,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(accountService);
		return daoAuthenticationProvider;
	}
	
}
