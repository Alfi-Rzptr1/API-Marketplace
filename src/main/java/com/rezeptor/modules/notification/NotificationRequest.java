package com.rezeptor.modules.notification;

public record NotificationRequest(
		String title,
		String message,
		long accountDataId
		) {

}
