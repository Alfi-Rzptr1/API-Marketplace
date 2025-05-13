package com.rezeptor.modules.ItemParrentVariant;

import java.util.Set;

import com.rezeptor.modules.itemVariant.ItemVariantRequest;

public record ItemParrentVariantRequest(
		long itemId,
		Set<ItemVariantRequest> itemVariantRequests,
		String name
		) {

}
