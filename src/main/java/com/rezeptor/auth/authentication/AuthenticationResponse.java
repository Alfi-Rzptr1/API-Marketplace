package com.rezeptor.auth.authentication;

import com.rezeptor.modules.account.AccountDTO;

public record AuthenticationResponse(
		String token,
		AccountDTO accountDTO
		) {

}
