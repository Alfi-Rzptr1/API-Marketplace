package com.rezeptor.auth.registration;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.modules.address.Address;

import jakarta.validation.constraints.Email;

public record RegistrationRequest(
		String username,
		@Email String email,
		String password,
		String firstName,
		String lastName,
		String birthDate,
		String gender,
		Set<Address> addresses,
		MultipartFile accountImage,
		
		//merchant role
		String shopName,
		String domain,
		String description
		) {

}
