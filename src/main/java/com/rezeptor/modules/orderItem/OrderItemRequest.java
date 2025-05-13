package com.rezeptor.modules.orderItem;

public record OrderItemRequest(
		long orderParrentId,
		long buyerAccout,
		long merchantAccount,
		OrderType orderType,
		long itemId,
		int quantity,
		String paymentMethod,
		OrderStatus orderStatus
		) {

}
