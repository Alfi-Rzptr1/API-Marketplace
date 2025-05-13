package com.rezeptor.modules.item;

import java.util.Set;

import com.rezeptor.modules.itemVariant.ItemVariantRequest;

public record ItemRequest(
		String name,
		boolean isCOD,
		long accountId,
		long itemCategoryId,
		Set<ItemVariantRequest> itemVariantRequests
		) {

}