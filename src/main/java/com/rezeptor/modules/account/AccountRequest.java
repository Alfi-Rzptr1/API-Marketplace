package com.rezeptor.modules.account;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.modules.address.Address;

public record AccountRequest(
		//basic role
		String username,
		String email,
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
