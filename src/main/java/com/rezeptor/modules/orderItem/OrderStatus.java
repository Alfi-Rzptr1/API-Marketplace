package com.rezeptor.modules.orderItem;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
	PENDING,
  	PROCESSED,
  	SUCCEED,
  	FAIL,
  	WAITING,
  	CANCELLED_BY_SYSTEM,
  	CANCELLED_BY_MERCHANT,
  	CANCELLED_BY_BUYER;
	
	@JsonCreator
	public static OrderStatus fromValue(String value) {
		return OrderStatus.valueOf(value.toUpperCase());
	}
}
