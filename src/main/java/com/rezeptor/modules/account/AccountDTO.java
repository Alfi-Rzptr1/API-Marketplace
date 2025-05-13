package com.rezeptor.modules.account;

import java.util.List;

public record AccountDTO(
		Long id,
		String username,
		String firstName,
		String lastName,
		String email,
		List<String> roles,
		Boolean enabled,
		Boolean deleted
		){

}
