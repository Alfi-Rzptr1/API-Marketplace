package com.rezeptor.modules.cartItem;

public record CartItemRequest(
		long cartParrentId,
		long accountMerchantId,
		long itemParrentId,
		long itemVariantid,
		int quantity
		) {

}
