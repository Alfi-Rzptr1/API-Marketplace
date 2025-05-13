package com.rezeptor.modules.orderItem;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderType {
	SELLING,
	PURCHASING;
	
	@JsonCreator
	public static OrderType fromValue(String value) {
		return OrderType.valueOf(value.toUpperCase());
	}
}
